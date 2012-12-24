<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="playerList.title"/></title>
    <meta name="menu" content="PlayerMenu"/>
</head>

<div class="span10">
    <h2><fmt:message key="playerList.heading"/></h2>
    <form method="post" action="${ctx}/players" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i>
            <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <div id="actions" class="form-actions">
        <a class="btn btn-primary" href="<c:url value='/editPlayer.html'/>" >
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/>
        </a>
        <a class="btn" href="<c:url value="/players"/>">
            <i class="icon-ok"></i> <fmt:message key="button.done"/>
        </a>
    </div>

    <s:set name="now" value="@java.lang.System@currentTimeMillis()"/>

    <display:table name="players" cellspacing="0" cellpadding="0" requestURI=""
                   defaultsort="1" id="players" pagesize="50" class="table table-condensed table-striped table-hover" export="true">
        <display:column property="name" escapeXml="true" sortable="true" titleKey="player.name" style="width: 35%"
                        url="/editPlayer" paramId="id" paramProperty="id"/>
        <display:column property="address" sortable="true" titleKey="player.address" style="width: 20%"/>
        <display:column property="lastHeartbeat" sortable="true" titleKey="player.lastHeartbeat" style="width: 25%" media="html"/>
        <display:column sortProperty="enabled" sortable="true" titleKey="player.enabled"
                        style="width: 10%; padding-left: 15px" media="html">
            <input type="checkbox" disabled="disabled" <c:if test="${players.enabled}">checked="checked"</c:if>/>
        </display:column>
        <display:column property="enabled" titleKey="player.enabled" media="csv xml excel pdf"/>
        <display:column titleKey="player.health" style="width: 10%" media="html">
			<s:if test="%{#now-#attr.players.lastHeartbeat.time<=600000}">
			    <img src="<c:url value='/images/signal_5.gif'/>">
			</s:if>
			<s:elseif test="%{#now-#attr.players.lastHeartbeat.time<=1800000}">
			    <img src="<c:url value='/images/signal_4.gif'/>">
			</s:elseif>
			<s:elseif test="%{#now-#attr.players.lastHeartbeat.time<=7200000}">
			    <img src="<c:url value='/images/signal_3.gif'/>">
			</s:elseif>
			<s:elseif test="%{#now-#attr.players.lastHeartbeat.time<=86400000}">
			    <img src="<c:url value='/images/signal_2.gif'/>">
			</s:elseif>
			<s:elseif test="%{#now-#attr.players.lastHeartbeat.time<=172800000}">
			    <img src="<c:url value='/images/signal_1.gif'/>">
			</s:elseif>
			<s:else>
			    <img src="<c:url value='/images/signal_0.gif'/>">
			</s:else>
        </display:column>

        <display:setProperty name="paging.banner.item_name"><fmt:message key="playerList.player"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="playerList.players"/></display:setProperty>

        <display:setProperty name="export.excel.filename" value="Player List.xls"/>
        <display:setProperty name="export.csv.filename" value="Player List.csv"/>
        <display:setProperty name="export.pdf.filename" value="Player List.pdf"/>
    </display:table>
</div>
