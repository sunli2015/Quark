
var coralValidator = new CoralValidationClient("$!base/validation");
coralValidator.onErrors = function (form, errors) {
	var errorstr = "";
	for(var i=0;i<form.elements.length;i++){
		if(form.elements[i].className=="inputorError"){
			form.elements[i].className="";
		}
	}
	if (errors.fieldErrors) {
		for (var fieldName in errors.fieldErrors) {
			if (form.elements[fieldName]) {
				var temp = "";
				for (var i = 0; i < errors.fieldErrors[fieldName].length; i++) {
					temp = errors.fieldErrors[fieldName][i]+"\n";
				}
				errorstr += temp;
				form.elements[fieldName].className="inputorError";
			}
		}
	}
	if(errorstr === ""){
		Form.enable(form);
	 	form.submit();
	 }else{
	 	alert(errorstr);
	 	Form.enable(form);
	 }
};
function coralValidate(form) {
	var namespace = form.attributes["namespace"].nodeValue;
	var actionName = form.attributes["name"].nodeValue;
	coralValidator.validate(form, namespace, actionName);	
	return false;
}
function CoralValidationClient(servletUrl) {
	this.servletUrl = servletUrl;
	this.validate = function (form, namespace, actionName) {
		Form.disable(form);
		var vc = this;
		var params = new Object();
		for (var i = 0; i < form.elements.length; i++) {
			var e = form.elements[i];
			if (e.name !== null && e.name !== "") {
				params[e.name] = e.value;
			}
		}
		validator.doPost(namespace, actionName, params,function (action) {
			if (action) {
				vc.onErrors(form, action);
			}
		});
	};
	return this;
}

