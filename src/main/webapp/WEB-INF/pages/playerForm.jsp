<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="playerDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='playerDetail.heading'/>"/>
</head>

<script>
  $(document).ready(function(){
	$("#playerKeies").treeview({
		animated:"normal",
		persist: "cookie"
	});
  });
</script>

<div class="span2">
    <h2><fmt:message key="playerDetail.heading"/></h2>
    <p><fmt:message key="playerDetail.message"/></p>
</div>

<div class="span7">
	<s:form id="playerForm" action="savePlayer" method="post" validate="true" cssClass="well form-horizontal" autocomplete="off">
	    <s:hidden name="player.id" value="%{player.id}"/>
	    <s:hidden key="player.enabled"/>
	
	    <s:textfield key="player.name" required="true" readonly="true"/>

		<fieldset class="control-group">
			<label class="control-label"><fmt:message key="player.playerKeies"/>:</label>
			<div class="controls readonly">
				<ul id="playerKeies" class="filetree">
			    <s:iterator id="playerKey" value="player.playerKeies">
			    	<s:property value="#playerKey" escape="false"/>
			    </s:iterator>
				</ul>
			</div>
		</fieldset>
		
	    <s:textfield key="player.address" required="true"/>
	    <s:textfield key="player.lastHeartbeat" required="true" readonly="true"/>

		<fieldset class="control-group">
			<label class="control-label"><fmt:message key="player.enabled"/>:</label>
			<div class="controls readonly">
				<s:checkbox key="player.enabled" theme="simple" disabled="true"/>
			</div>
		</fieldset>
    
        <div id="actions" class="form-actions">
            <s:submit type="button" cssClass="btn btn-primary" method="save" key="button.save" theme="simple">
                <i class="icon-ok icon-white"></i>
                <fmt:message key="button.save"/>
            </s:submit>
            <c:if test="${param.from == 'list' and not empty player.id}">
                <s:submit type="button" cssClass="btn btn-danger" method="delete" key="button.delete"
                    onclick="return confirmMessage(msgDelConfirm)" theme="simple">
                    <i class="icon-trash"></i>
                    <fmt:message key="button.delete"/>
                </s:submit>
            </c:if>
            <s:submit type="button" cssClass="btn" method="cancel" key="button.cancel" theme="simple">
                <i class="icon-remove"></i>
                <fmt:message key="button.cancel"/>
            </s:submit>
        </div>
	</s:form>
</div>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['userForm']).focus();
    });
</script>
