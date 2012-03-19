package com.sociorespo.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sociorespo.bl.FaceBookBL;
import com.sociorespo.dto.ProfileDTO;

import actionform.ProfileActionForm;
import bl.ProfileBL;

public class ProfileAction extends Action{

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException{
		

		
		String nextPage =null;
		String finalResult=null;
		HttpSession session = null;
		
		ActionErrors errors = new ActionErrors();
		
		try{
		
		session = request.getSession(true);
		
		ProfileActionForm profileActionForm = (ProfileActionForm)form;
		
		ProfileBL profileBL = new ProfileBL();
		
		int userId = 1;
		
		FaceBookBL faceBookBL = new FaceBookBL();
		
		ProfileDTO profileDTO = new ProfileDTO();
		
		
		profileDTO = faceBookBL.getFaceBookProfile(userId);
		
		
		
		errors.add("PIMERROR", new ActionError("errors.pim.profileupdated.success"));
		
		}catch(Exception e){
			e.printStackTrace();
			errors.add("PIMERROR", new ActionError(
			"errors.pim.internalerror"));
			
			nextPage="EXCEPTION";
		}
		
		if (!errors.isEmpty()) {
			saveErrors(request, errors);			
		} else {
		}
		
		
		nextPage="SUCCESS";
		
		return mapping.findForward(nextPage);
		
	}
}
