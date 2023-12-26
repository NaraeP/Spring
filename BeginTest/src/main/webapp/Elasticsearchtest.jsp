<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Example</title>
<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">
<style>
	iframe {
		width: 1190px;
		margin: 0 auto;
		border: 0;
	}
</style>
</head>
<body> 
	<!-- Elasticsearchtest -->
	<h1>Elasticsearch <small>DashBoard></small></h1>

	<iframe src="http://localhost:5601/app/dashboards#/view/a3b5c530-9e13-11ee-8ec2-253de87dee05?embed=true&_g=(filters:!(),refreshInterval:(pause:!t,value:0),time:(from:now-30y,to:now))&_a=(description:'',filters:!(),fullScreenMode:!f,options:(hidePanelTitles:!f,syncColors:!f,useMargins:!t),panels:!((embeddableConfig:(enhancements:(),savedVis:(data:(aggs:!((enabled:!t,id:'1',params:(),schema:metric,type:count),(enabled:!t,id:'2',params:(field:buseo,missingBucket:!f,missingBucketLabel:Missing,order:desc,orderBy:'1',otherBucket:!f,otherBucketLabel:Other,size:5),schema:segment,type:terms)),searchSource:(filter:!(),index:a1f11220-9e10-11ee-9efd-77c7732d60fe,query:(language:kuery,query:''))),description:'',id:'',params:(addLegend:!t,addTimeMarker:!f,addTooltip:!t,categoryAxes:!((id:CategoryAxis-1,labels:(filter:!t,show:!t,truncate:100),position:bottom,scale:(type:linear),show:!t,style:(),title:(),type:category)),detailedTooltip:!t,grid:(categoryLines:!f),labels:(show:!f),legendPosition:right,maxLegendLines:1,palette:(name:default,type:palette),radiusRatio:0,row:!t,seriesParams:!((circlesRadius:1,data:(id:'1',label:Count),drawLinesBetweenPoints:!t,interpolate:linear,lineWidth:2,mode:stacked,show:!t,showCircles:!t,type:histogram,valueAxis:ValueAxis-1)),thresholdLine:(color:%23E7664C,show:!f,style:full,value:10,width:1),times:!(),truncateLegend:!t,type:histogram,valueAxes:!((id:ValueAxis-1,labels:(filter:!f,rotate:0,show:!t,truncate:100),name:LeftAxis-1,position:left,scale:(mode:normal,type:linear),show:!t,style:(),title:(text:''),type:value))),title:'%EB%B6%80%EC%84%9C%EB%B3%84%20%EC%9D%B8%EC%9B%90%EC%88%98',type:histogram,uiState:())),gridData:(h:15,i:'839dcdbf-70ef-45a6-aaa4-4d1bccdc99b9',w:24,x:0,y:0),panelIndex:'839dcdbf-70ef-45a6-aaa4-4d1bccdc99b9',type:visualization,version:'7.17.15'),(embeddableConfig:(enhancements:(),savedVis:(data:(aggs:!((enabled:!t,id:'1',params:(),schema:metric,type:count),(enabled:!t,id:'2',params:(field:buseo,missingBucket:!f,missingBucketLabel:Missing,order:desc,orderBy:'1',otherBucket:!f,otherBucketLabel:Other,size:5),schema:segment,type:terms),(enabled:!t,id:'3',params:(field:position,missingBucket:!f,missingBucketLabel:Missing,order:desc,orderBy:'1',otherBucket:!f,otherBucketLabel:Other,size:5),schema:segment,type:terms)),searchSource:(filter:!(),index:a1f11220-9e10-11ee-9efd-77c7732d60fe,query:(language:kuery,query:''))),description:'',id:'',params:(addLegend:!f,addTooltip:!t,distinctColors:!f,isDonut:!t,labels:(last_level:!f,percentDecimals:2,position:default,show:!t,truncate:100,values:!t,valuesFormat:percent),legendPosition:right,maxLegendLines:1,nestedLegend:!f,palette:(name:default,type:palette),truncateLegend:!t,type:pie),title:'%EB%B6%80%EC%84%9C%EB%B3%84%20%EC%A7%81%EA%B8%89',type:pie,uiState:(vis:(legendOpen:!f)))),gridData:(h:15,i:e77c69cc-e17f-4851-8365-b66449ec5ed0,w:24,x:24,y:0),panelIndex:e77c69cc-e17f-4851-8365-b66449ec5ed0,type:visualization,version:'7.17.15'),(embeddableConfig:(savedVis:(data:(aggs:!((enabled:!t,id:'1',params:(),schema:metric,type:count),(enabled:!t,id:'2',params:(field:buseo,missingBucket:!f,missingBucketLabel:Missing,order:desc,orderBy:'1',otherBucket:!f,otherBucketLabel:Other,size:5),schema:split,type:terms)),searchSource:(filter:!(),index:a1f11220-9e10-11ee-9efd-77c7732d60fe,query:(language:kuery,query:''))),description:'',id:'',params:(autoFitRowToContent:!f,perPage:10,percentageCol:'',row:!t,showMetricsAtAllLevels:!f,showPartialRows:!f,showToolbar:!f,showTotal:!f,totalFunc:sum),title:'%EB%B6%80%EC%84%9C%EB%B3%84%20%EC%9D%B8%EC%9B%90%EC%88%98',type:table,uiState:())),gridData:(h:15,i:'9c7097ea-e9ba-4b8d-a40c-859e7c1a6684',w:24,x:0,y:15),panelIndex:'9c7097ea-e9ba-4b8d-a40c-859e7c1a6684',type:visualization,version:'7.17.15')),query:(language:kuery,query:''),tags:!(),timeRestore:!f,title:%EC%9D%B8%EC%82%AC,viewMode:edit)" height="600" width="800"></iframe>


	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script>
	
	</script>
</body>
</html>