package com.datastax.yasa.ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.WebContext;

import com.datastax.dse.driver.api.core.DseSession;
import com.datastax.oss.driver.api.core.config.DefaultDriverOption;
import com.datastax.oss.driver.api.core.config.DriverOption;
import com.datastax.oss.driver.api.core.metadata.Node;
import com.datastax.yasa.ui.webbean.DseNodeWebBean;
import com.datastax.yasa.ui.webbean.HomeWebBean;

@Controller
@RequestMapping(value="/")
public class HomeController extends AbstractController {
    
    /** Vie name. */
    private static final String HOME_VIEW = "home";
    
    @Autowired
    private CqlSession cqlSession;

    /** {@inheritDoc} */
    @Override
    public String getSuccessView() {
        return HOME_VIEW;
    }
    
    /** {@inheritDoc} */
    @Override
    public void get(HttpServletRequest req, HttpServletResponse res, WebContext ctx) 
    throws Exception {
        ctx.setVariable("homebean", mapSession(cqlSession));
    }
    
    /** {@inheritDoc} */
    @Override
    public void processPost(HttpServletRequest req, HttpServletResponse res, WebContext ctx) 
    throws Exception {
    }
    
    /**
     * Map to web bean.
     *
     * @param cqlSession
     *          current datastax Cluster instance
     * @return
     *      werb bean
     */
    private HomeWebBean mapSession(final CqlSession cqlSession) {
        HomeWebBean home = new HomeWebBean();
        home.setClusterName(cqlSession.getName());
        home.setDseDriverVersion(cqlSession.getContext().getProtocolVersion().name());
        for (Node apolloNode : cqlSession.getMetadata().getNodes().values()) {
            DseNodeWebBean dseNode = new DseNodeWebBean();
            dseNode.setHostname(apolloNode.getHostId().toString());
            dseNode.setHostAdress(apolloNode.getEndPoint().toString());
            dseNode.setDseVersion(apolloNode.getCassandraVersion().toString());
            dseNode.setState(apolloNode.getState().name());
            dseNode.getWorkloads().add("Cassandra");
            home.getDseNodeList().add(dseNode);
        }
        
        // Po licies
        home.setLoadBalancingPolicy(getDriverOption(cqlSession, DefaultDriverOption.LOAD_BALANCING_POLICY_CLASS));
        home.setReconnectionPolicy(getDriverOption(cqlSession, DefaultDriverOption.RECONNECTION_POLICY_CLASS));
        home.setRetryPolicy(getDriverOption(cqlSession, DefaultDriverOption.RETRY_POLICY_CLASS));
        
        // Query Options
        home.setQueryRefreshNodeInterval(0);
        home.setQueryRefreshNodeMaxPendingRequest(0);
        home.setQueryRefreshNodeListInterval(0);
        home.setQueryRefreshNodeListMaxPendingRequest(0);
        home.setQueryRefreshSchemaInterval(0);
        home.setQueryRefreshSchemaPendingRequest(0);
        home.setQueryConsistencyLevel(getDriverOption(cqlSession, DefaultDriverOption.REQUEST_CONSISTENCY));
        home.setQueryFetchSize(
                cqlSession.getContext().getConfig().getDefaultProfile().getInt(DefaultDriverOption.REQUEST_PAGE_SIZE));
        home.setQueryDefaultIdempotence(true);
        return home;
    }
    
    private String getDriverOption(final CqlSession cqlSession, DriverOption option) {
        return cqlSession.getContext().getConfig().getDefaultProfile().getString(option);
    }
  
   
}
