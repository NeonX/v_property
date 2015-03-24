function downloadFileAttach(url){
	window.open (url);
}
function getPlanProgressReport(context, projId){
	window.open (context+"/report/planandprogress.html?pid="+projId,"BarChart","scrollbars=1,width=1024,height=780,resizable=1,menubar=1");
}
function manageRowColor(obj, skinVal){
	if(obj.style.backgroundColor == ''){
		obj.style.backgroundColor = "#ffffaa";
	}else{
		obj.style.backgroundColor = '';
	}
}
function suspendAlertInfo(){
	alert("ฟังก์ชั่นนี้ยังไม่เปิดใช้งาน");
}
function logoutNormalIcon(obj, context){
	obj.src = context+"/img/shut_down.png";
}
function logoutGlowIcon(obj, context){
	obj.src = context+"/img/shut_down_glow.png";
}

function setWaitCursor(){
	document.body.style.cursor="wait";
}

function setDefaultCursor(){
	document.body.style.cursor="default";
}

function autoSeperatorII(element){
	var elmtVal = element.value;
	
	var hasLastDot = false;
	if(elmtVal.length > 0){
		if(elmtVal.charAt(elmtVal.length-1) == "."){
			hasLastDot = true;
		}
	}
	
	var arrVal = elmtVal.split(",");
	if(arrVal.length > 0){
		var num = "";
		for(var i=0; i < arrVal.length; i++){
			num += arrVal[i];
		}

		if(isNumberValue(num)){
			var result = FormatNumberBy3(num);
			if(hasLastDot){
				element.value = result+".";
			}else{
				element.value = result;
			}
		}else{
			alert("กรุณากรอกเป็นตัวเลข");
			element.value = "";
			//element.style.borderColor = "#ff0000";
			element.focus();
		}
		
	}
}
function autoSeperator(element){
	var elmtVal = element.value;
	
	var hasLastDot = false;
	if(elmtVal.length > 0){
		if(elmtVal.charAt(elmtVal.length-1) == "."){
			hasLastDot = true;
		}
	}
	
	var arrVal = elmtVal.split(",");
	if(arrVal.length > 0){
		var num = "";
		for(var i=0; i < arrVal.length; i++){
			num += arrVal[i];
		}

		if(isNumberValue(num)){
			var result = FormatNumberBy3(num);
			if(hasLastDot){
				element.value = result+".";
			}else{
				element.value = result;
			}
		}else{
			alert("กรุณากรอกเป็นตัวเลข");
			element.value = "";
			element.style.borderColor = "#ff0000";
			element.focus();
		}
		
	}
}