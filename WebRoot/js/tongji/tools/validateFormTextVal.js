//1.js验证只能输入数字.
function check_validateNumber(value){
       var reg = new RegExp("^[0-9]*$");
	   if(reg.test(value)){
			return true;
	   }
	   return false;
}