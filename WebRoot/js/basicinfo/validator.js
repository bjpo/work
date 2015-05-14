$.extend($.fn.validatebox.defaults.rules, {
	idcard:{
		validator: function (value, param) {
		    return /^\d{15}(\d{2}[A-Za-z0-9])?$/.test(value);
		},
        message: '身份证号码格式不正确'
    },
    phone:{
		validator: function (value, param) {
		    return /^((\(\d{3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}$/.test(value);
		},
        message: '固定电话格式：0451-00000000'
    },
    zipcode:{
		validator: function (value, param) {
		    return  /^[1-9]\d{5}$/.test(value);
		},
        message: '邮编不正确'
    },
    integer:{
		validator: function (value, param) {
		    return  /^[-\+]?\d+$/.test(value);
		},
        message: '此处输入整数'
    },
    mobile: {
        validator: function (value, param) {
            return /^((\(\d{2,3}\))|(\d{3}\-))?13\d{9}$/.test(value);
        },
        message: '手机号码不正确'
    },
    picture: {
        validator: function (value, param) {
            return /\.(JPG|JPEG|jpg|jpeg)$/.test(value);
        },
        message: '请选择(JPG|JPEG|jpg|jpeg)格式的图片'
    }

});