var popLayer	= false;
var loding		= false;

function _lodingOn() {
	$('#lodingDiv').css('visibility', 'visible');
	loding	= true;
}
function _lodingOff() {
	$('#lodingDiv').css('visibility', 'hidden');
	loding	= false;
}
function _showModal(path, formId, inputId, inputVal, modalId) {
	if(inputId != '' && inputVal != '') {
		$('#'+ inputId).val(inputVal);
	}
	
	var formData = $("form[id="+formId+"]").serialize();
	$.ajax({
		url			: path,
		type		: "post",
		data		: formData,
		dataType	: "html",
		async		: false,
		success: function(modalData) {
			$('#'+ modalId +'-content').html(modalData);
			$('#'+ modalId).modal({backdrop: 'static'});
		}
	});
}


function _showModalForm(path, formId, inputId, inputVal, modalId, width, height) {
	if(inputId != '' && inputVal != '') {
		$('#'+ inputId).val(inputVal);
	}
	
	var formData = $("form[id="+formId+"]").serialize();
	$.ajax({
		url			: path,
		type		: "post",
		data		: formData,
		dataType	: "html",
		async		: false,
		success: function(modalData) {
			$('#'+ modalId +'-content').html(modalData);
			$('#'+ modalId).modal({backdrop: 'static'});
			
             // Set max-width for the modal
            $('#' + modalId + ' .modal-dialog').css('max-width', 'none');
            $('#' + modalId + ' .modal-dialog').css('max-height', 'none');			
            // Set modal width and height
            if (width) {
                $('#' + modalId + ' .modal-dialog').css('width', width);
            }
            if (height) {
                $('#' + modalId + ' .modal-content').css('max-height', height);
            }

		}
	});
}




function _showModal2(path, formId, inputId, inputVal, modalId) {
	if(inputId != '' && inputVal != '') {
		$('#'+ inputId).val(inputVal);
	}
	
	var formData = $('#'+ formId).serialize();
	$.ajax({
		url			: path,
		type		: "post",
		data		: formData,
		dataType	: "html",
		async		: false,
		success: function(modalData) {
			$('#'+ modalId +'-content').html(modalData);
			$('#'+ modalId).modal({backdrop: 'static'});
		}
	});
}

function _hideModal(modalId) {
	$('#'+ modalId).modal({'data-dismiss' : 'modal'});
}

function _showModalMsg(modalId, modalMsg, nextAct, nextVal) {
	$('#'+ modalId).modal({backdrop: 'static'});
}

function _showAlert(msgTitle, msgText, msgIcon, nextAction, nextTarget) {
	Swal.fire({
		title:	msgTitle,
		text:	msgText,
		icon:	msgIcon,
		showCancelButton:	false,
		confirmButtonColor:	'#3085d6',
		confirmButtonText:	'확인',
		cancelButtonColor:	'#d33',
		cancelButtonText:	'취소'
	}).then((result) => {
		if (result.isConfirmed) {
			switch(nextAction) {
				case "focus":	// 마우스 포커스
					$("#"+ nextTarget).focus();
					break;
				case "tlink":	// 상위 페이지 이동
					top.location.href	= nextTarget;
					break;
				case "llink":	// 현재 페이지 이동
					location.href		= nextTarget;
					break;
				case "lload":	// 현재 페이지 이동
					document.location.reload(true);
					break;
				case "submit":	// 폼 서브밋
					$("#"+ nextTarget).submit();
					break;
				case "script":	// 스크립트 실행
					(new Function(nextTarget))();
					break;
				case "back":	// 뒷페이지 이동
					history.back();
					break;
			}
		}
	});
}


function _showConfirm(msgTitle, msgText, msgIcon, nextAction, nextTarget) {
	Swal.fire({
		title:	msgTitle,
		text:	msgText,
		icon:	msgIcon,
		showCancelButton:	true,
		confirmButtonColor:	'#3085d6',
		confirmButtonText:	'확인',
		cancelButtonColor:	'#d33',
		cancelButtonText:	'취소'
	}).then((result) => {
		if (result.isConfirmed) {
			switch(nextAction) {
				case "focus":	// 마우스 포커스
					$("#"+ nextTarget).focus();
					break;
				case "tlink":	// 상위 페이지 이동
					top.location.href	= nextTarget;
					break;
				case "llink":	// 현재 페이지 이동
					location.href		= nextTarget;
					break;
				case "lload":	// 현재 페이지 이동
					document.location.reload(true);
					break;
				case "submit":	// 폼 서브밋
					$("#"+ nextTarget).submit();
					break;
				case "script":	// 스크립트 실행
					(new Function(nextTarget))();
					break;
				case "back":	// 뒷페이지 이동
					history.back();
					break;
			}
		}
	});
}

function _movePage(moveUrl) {
	_lodingOn();
	location.href	= moveUrl;
}

function _submitPage(frmId, frmAction) {
	_lodingOn();
	$("form[id="+frmId+"]").attr("action", frmAction);
	$("form[id="+frmId+"]").submit();
	
}
function _submitPage2(frmId, frmAction) {
	_lodingOn();
	$("#"+ frmId).attr("action", frmAction);
	$("#"+ frmId).submit();
}

function _submitGoPage(frmId, pageNum) {
	_lodingOn();
	$("#pageNum").val(pageNum);
	$("form[id="+frmId+"]").submit();
	//$("#"+ frmId).submit();
}


function _menuAuthCheck(userLevel, authFlag, authLink, authUse, authRead, authWrite, authDel) {
	// 회원권한이 9가 아닐때
	if(userLevel != '9') {
		if(authUse != 'Y' && authLink != '') {
			//_popMagToggle('접근 불가한 메뉴 입니다.<br>관리자에게 문의 바랍니다.', '', '');
			_showAlert('', '접근 불가한 메뉴 입니다. 관리자에게 문의 바랍니다.', 'error', '', '');
			return false;
		}
		if(authRead != 'Y' && authLink != '') {
			//_popMagToggle('접근 권한이 부족 합니다.<br>관리자에게 문의 바랍니다.', '', '');
			_showAlert('', '접근 권한이 부족 합니다. 관리자에게 문의 바랍니다.', 'error', '', '');
			return false;
		}
	}
	if(authLink.replace(" ","") != '') {
		_movePage(authLink);
	}
}

function _authCheck(userLevel, authFlag, authStat, linkUrl, linkType) {
	if(userLevel != '9') {
		switch(authFlag) {
			case 'use':
				if(authStat != 'Y') {
					_showAlert('', '접근 불가한 메뉴 입니다. 관리자에게 문의 바랍니다.', 'error', '', '');
					return false;
				}
				break;
			case 'read':
				if(authStat != 'Y') {
					_showAlert('', '읽기 권한이 부족 합니다. 관리자에게 문의 바랍니다.', 'error', '', '');
					return false;
				}
				break;
			case 'write':
				if(authStat != 'Y') {
					_showAlert('', '쓰기 권한이 부족 합니다. 관리자에게 문의 바랍니다.', 'error', '', '');
					return false;
				}
				break;
			case 'edit':
				if(authStat != 'Y') {
					_showAlert('', '수정 권한이 부족 합니다. 관리자에게 문의 바랍니다.', 'error', '', '');
					return false;
				}
				break;
			case 'delete':
				if(authStat != 'Y') {
					_showAlert('', '삭제 권한이 부족 합니다. 관리자에게 문의 바랍니다.', 'error', '', '');
					return false;
				}
				break;
		}
	}

	if(linkType == 'move') {
		if(linkUrl.replace(" ","") != '') {
			_movePage(linkUrl);
		}
		return false;
	} else {
		return true;
	}
}

function _fileDown(sendUrl, fileFlag, fileSeq, fileName, fileType) {
	location.href = sendUrl +"?fileFlag="+ fileFlag +"&fileSeq="+ fileSeq +"&fileName="+ fileName +"&fileType="+ fileType;
}

function _fileDel(fileSeq, fileNum, fileType) {
	_lodingOn();
	$.ajax({
		url			: "/FileDelete.do",
		type		: "POST",
		data		: {"fileSeq":fileSeq,"fileNum":fileNum,"fileType":fileType},
		dataType	: "json",
		success:function(data) {
			if(data.rtnFlag == "Y") {
				document.location.reload(true);
				return false;
			} else {
				_lodingOff();
				_popMagToggle(data.rtnMsg, '', '');
				return false;
			}
		}
	});
}

function _downExcel(downUrl, sendFrm) {
	
	_lodingOn();
	
	var request	= new XMLHttpRequest();
	request.open('POST', downUrl, true);
	request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
	request.responseType = 'blob';
	request.onload = function(e) {
		_lodingOff();
		var filename	= "";
		var disposition	= request.getResponseHeader('Content-Disposition');
		if (disposition && disposition.indexOf('attachment') !== -1) {
			var filenameRegex	= /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
			var matches	= filenameRegex.exec(disposition);
			if (matches != null && matches[1])
				filename = decodeURI( matches[1].replace(/['"]/g, '') );
		}
		if (this.status === 200) {
			var blob = this.response;
			if(window.navigator.msSaveOrOpenBlob) {
				window.navigator.msSaveBlob(blob, filename);
			} else {
				var downloadLink = window.document.createElement('a');
				var contentTypeHeader = request.getResponseHeader("Content-Type");
				downloadLink.href = window.URL.createObjectURL(new Blob([blob], { type: contentTypeHeader }));
				downloadLink.download = filename;
				document.body.appendChild(downloadLink);
				downloadLink.click();
				document.body.removeChild(downloadLink);
			}
		}
	};
	var params = $("form[id="+sendFrm+"]").serialize();
	
	request.send(params);
}


function _downNotice(downUrl, sendFrm, fileName, filePath) {
	_lodingOn();
	$("#fileName").val(fileName);
	$("#filePath").val(filePath);
	
	
	var request	= new XMLHttpRequest();
	request.open('POST', downUrl, true);
	request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
	request.responseType = 'blob';
	request.onload = function(e) {
		_lodingOff();
		var filename	= "";
		var disposition	= request.getResponseHeader('Content-Disposition');
		if (disposition && disposition.indexOf('attachment') !== -1) {
			var filenameRegex	= /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
			var matches	= filenameRegex.exec(disposition);
			if (matches != null && matches[1])
				filename = decodeURI( matches[1].replace(/['"]/g, '') );
		}
		if (this.status === 200) {
			var blob = this.response;
			if(window.navigator.msSaveOrOpenBlob) {
				window.navigator.msSaveBlob(blob, filename);
			} else {
				var downloadLink = window.document.createElement('a');
				var contentTypeHeader = request.getResponseHeader("Content-Type");
				downloadLink.href = window.URL.createObjectURL(new Blob([blob], { type: contentTypeHeader }));
				downloadLink.download = filename;
				document.body.appendChild(downloadLink);
				downloadLink.click();
				document.body.removeChild(downloadLink);
			}
		}
	};
	var params = $("form[id="+sendFrm+"]").serialize();
	request.send(params);
}

function _convertSize(setByte) {
	var rtnSize = "0 Byte";
	
	if(setByte > 1024) {
		var setKB = setByte / 1024;
		if(setKB > 1024) {
			var setMB = setKB / 1024;
			if(setMB > 1024) {
				var setGB = setMB / 1024;
				if(setGB > 1024) {
					var setTB = setGB / 1024;
					if(setTB > 1024) {
						var setPB = setTB / 1024;
						if(setPB > 1024) {
							var setEB = setPB / 1024;
							if(setEB > 1024) {
								var setZB = setEB / 1024;
								if(setZB > 1024) {
									var setYB = setZB / 1024;
									rtnSize	= setYB.toFixed(1) +" YB";
								} else {
									rtnSize	= setZB.toFixed(1) +" EB";
								}
							} else {
								rtnSize	= setEB.toFixed(1) +" EB";
							}
						} else {
							rtnSize	= setPB.toFixed(1) +" PB";
						}
					} else {
						rtnSize	= setTB.toFixed(1) +" TB";
					}
				} else {
					rtnSize	= setGB.toFixed(1) +" GB";
				}
			} else {
				rtnSize	= setMB.toFixed(1) +" MB";
			}
		} else {
			rtnSize	= setKB.toFixed(1) +" KB";
		}
	} else {
		rtnSize	= setByte.toFixed(1) +" Byte";
	}
	
	return rtnSize;
}

/** 연락처 형식 맞추기 **/
function _setPhone(input) {
	var inDate	= "";
	var outDate	= "";
	inDate	= $("#"+ input).val().replace(/[^0-9]/gi,'')
	
	if(inDate.length > 5) {
		if(inDate.substring(0, 2) == "02") {
			if(inDate.length > 9) {
				outDate	= inDate.substring(0, 2) +"-"+ inDate.substring(2, 6) +"-"+ inDate.substring(6);
			} else {
				outDate	= inDate.substring(0, 2) +"-"+ inDate.substring(2, 5) +"-"+ inDate.substring(5);
			}
		} else {
			if(inDate.length > 10) {
				outDate	= inDate.substring(0, 3) +"-"+ inDate.substring(3, 7) +"-"+ inDate.substring(7);
			} else {
				outDate	= inDate.substring(0, 3) +"-"+ inDate.substring(3, 6) +"-"+ inDate.substring(6);
			}
		}
	} else if(inDate.length > 2) {
		if(inDate.substring(0, 2) == "02") {
			outDate	= inDate.substring(0, 2) +"-"+ inDate.substring(2, inDate.length);
		} else  {
			outDate	= inDate.substring(0, 3) +"-"+ inDate.substring(3, inDate.length);
		}
	} else {
		outDate	= inDate;
	}
	$("#"+ input).val(outDate);
}
/** 날짜 형식 맞추기 **/
function _setDate(input) {
	var inDate	= "";
	var outDate	= "";
	inDate	= $("#"+ input).val().replace(/[^0-9]/gi,'')
	if(inDate.length > 6) {
		outDate	= inDate.substring(0, 4) +"-"+ inDate.substring(4, 6) +"-"+ inDate.substring(6);
	} else if(inDate.length > 4) {
		outDate	= inDate.substring(0, 4) +"-"+ inDate.substring(4, inDate.length);
	} else {
		outDate	= inDate;
	}
	$("#"+ input).val(outDate);
}
/** 사업자번호 형식 맞추기 **/
function _setRegNo(input) {
	var inDate	= "";
	var outDate	= "";
	inDate	= $("#"+ input).val().replace(/[^0-9]/gi,'')
	
	if(inDate.length > 5) {
		outDate	= inDate.substring(0, 3) +"-"+ inDate.substring(3, 5) +"-"+ inDate.substring(5);
	} else if(inDate.length > 3) {
		outDate	= inDate.substring(0, 3) +"-"+ inDate.substring(3, inDate.length);
	} else {
		outDate	= inDate;
	}
	$("#"+ input).val(outDate);
}