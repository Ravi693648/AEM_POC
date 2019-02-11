package com.aem.citi.core.workflow;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.jcr.Session;
import javax.jcr.Node;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import javax.jcr.Repository;
import javax.jcr.SimpleCredentials;
import javax.jcr.Node;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import com.day.cq.dam.api.Asset;
import java.util.Collections;

import org.apache.jackrabbit.commons.JcrUtils;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import javax.jcr.Session;
import javax.jcr.Node;
import org.osgi.framework.Constants;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

//Asset Manager
import com.day.cq.dam.api.AssetManager;

import java.io.BufferedOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;

//MessageGatewayService API
import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

//Sling imports
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.Resource;
import com.day.cq.wcm.api.Page;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.adobe.granite.workflow.model.WorkflowNode;
import com.adobe.granite.workflow.exec.WorkflowData;

//This custom workflow step will use the AEM Replication API to replicate content from Author to Publish
//This custom workflow step will use the AEM Replication API to replicate content from Author to Publish 

@Component(service=WorkflowProcess.class, property = {"process.label=My Email Custom Step"})


public class CustomStep implements WorkflowProcess {

	//Default log
	
	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Reference
	private ResourceResolverFactory resolverFactory;
	
	//Inject a messgeGatewayService
	@Reference
	private MessageGatewayService messageGatewayService;
	private Session session;
	
	public void execute (WorkItem item, WorkflowSession wfsession, MetaDataMap args) throws WorkflowException{
		
		try {
			
			log.info("Here in exeute mode");
			//Declare a messageGatewayService
			MessageGateway<Email> messageGateway;
			
			//setup the Email Message
			Email email = new SimpleEmail();
			
			//set the email values
			String emailToRecipients = "saraswatidhimant.antani@epsilon.com";
			String emailCcRecipients = "saraswatidihmant.antani@epsilon.com";
			
			email.addTo(emailToRecipients);
			email.addCc(emailCcRecipients);
			email.setSubject("AEM custom Step");
			email.setFrom("AEM admin");
			email.setMsg("This message is to inform you that AEM content has been deleted");
			
			//inject the message gateway service and set the message
			messageGateway = messageGatewayService.getGateway(Email.class);
			
			//check the logs to see the messageGateway is not null
			messageGateway.send((Email)email);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
}
