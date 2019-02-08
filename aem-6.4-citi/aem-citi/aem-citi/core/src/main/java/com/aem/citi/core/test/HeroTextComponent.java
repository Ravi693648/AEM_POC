package com.aem.citi.core.test;

import com.adobe.cq.sightly.WCMUsePojo;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;
import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
  
import com.aem.citi.core.test.HeroTextBean;
 
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.jcr.Node;
import javax.jcr.Session;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
 
 
public class HeroTextComponent
extends WCMUsePojo
{
	private static final Logger LOGGER = LoggerFactory.getLogger(HeroTextComponent.class);
     /** The hero text bean. */
    private HeroTextBean heroTextBean = null;
      
    @Override
    public void activate() throws Exception {
                  
        Node currentNode = getResource().adaptTo(Node.class);
         
        heroTextBean = new HeroTextBean();
          
        if(currentNode.hasProperty("jcr:Heading")){
            heroTextBean.setHeadingText(currentNode.getProperty("./jcr:Heading").getString());
        }
        if(currentNode.hasProperty("jcr:description")){
            heroTextBean.setDescription(currentNode.getProperty("./jcr:description").getString());
        }
        String param = getRequest().getParameter("tier").toString();
        LOGGER.info("my param::::"+param);
        
    }
      
      
      
    public HeroTextBean getHeroTextBean() {
        return this.heroTextBean;
    }
}