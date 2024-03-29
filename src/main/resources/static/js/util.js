/* class */

function addClass(elementId, className) {
    document.getElementById(elementId).classList.add(className);
}

function removeClass(elementId, className) {
    document.getElementById(elementId).classList.remove(className);
}

/* etc */

// alert
function openAlertModal(message) {
    document.getElementById('alertContent').innerText = message;
    addClass('alertBox', 'on');

    document.getElementById('alertCancelButton').style.display = 'none';
    document.getElementById('alertConfirmButton').setAttribute('onclick', 'closeAlert()');
}

// alert - 확인 버튼, confirm - 확인·취소 버튼
function closeAlert() {
    removeClass('alertBox', 'on');
}

// confirm
function openConfirmModal(message, callbackFunction) {
    document.getElementById('alertContent').innerText = message;
    addClass('alertBox', 'on');
    document.getElementById('alertCancelButton').style.display = 'inline-block';

    if(!isEmpty(callbackFunction))
        document.getElementById('alertConfirmButton').setAttribute('onclick', 'confirm_confirm(' + callbackFunction + ')');
}

// confirm - 확인 버튼 (콜백 함수 존재 시)
function confirm_confirm(callbackFunction) {
    closeAlert();
    if(typeof(callbackFunction) === 'function')
        callbackFunction();
}

// 빈 값 확인
function isEmpty(value) {
    if ((typeof value === "undefined") || (value === null) || (value === ""))
        return true;
    else
        return false;
}

// 값 복사
function copyToClipboard(value) {
    window.navigator.clipboard.writeText(value);
}

/* string */

// 문자열 추가 (왼쪽)
function lpad(value, length, text) {
    return value.padStart(length, text);
}

// 문자열 추가 (오른쪽)
function rpad(value, length, text) {
    return value.padEnd(length, text);
}

/* number */

// 숫자 여부 확인
function isNumber(value) {
    if(isNaN(value))
        return false;
    else
        return true;
}

// 홀수 확인
function isOdd(value) {
    if(!isNumber(value))
        return false;

    value = Number(value);

    if(value % 2 == 1)
        return true;
    else
        return false;
}

// 짝수 확인
function isEven(value) {
    if(!isNumber(value))
        return false;

    value = Number(value);

    if(value % 2 == 0)
        return true;
    else
        return false;
}

// 세 자리마다 ',' 추가
function addComma(value) {
    if(!isNumber(value))
        return value;

    value = Number(value);
    var valueStr = String(value);
    var resultStr = "";

    while(valueStr.length > 3) {
        resultStr = resultStr + "," + valueStr.substring(valueStr.length - 3, valueStr.length);
        valueStr = valueStr.substring(0, valueStr.length - 3);
    }

    resultStr = valueStr + resultStr;
    return resultStr;
}

/* formatter */

// '$' 표시 추가
function priceDollarFormatter(value) {
    if(!isNumber(value))
        return value;

    return "$" + addComma(value);
}

// '원' 표시 추가
function priceWonFormatter(value) {
    if(!isNumber(value))
        return value;

    return addComma(value) + "원";
}

// 네 자리마다 돈 단위 추가
function priceWonUnitFormatter(value) {
    if(!isNumber(value))
        return value;

    value = Number(value);
    var priceStr = String(value);
    var priceUnits = ['', '만', '억', '조', '경', '해'];
    var priceSplit = [];

    var repeatCount = 0;
    while(priceStr.length > 4) {
        priceSplit[repeatCount++] = priceStr.substring(priceStr.length - 4, priceStr.length);
        priceStr = priceStr.substring(0, priceStr.length - 4);
    }
    priceSplit[repeatCount] = priceStr;

    var resultStr = "";
    for(var i=priceSplit.length ; i>0 ; i--) {
        if(Number(priceSplit[i - 1]) == 0)
            continue;

        resultStr = resultStr + addComma(priceSplit[i - 1]) + priceUnits[i - 1] + ' ';
    }

    resultStr = isEmpty(resultStr) ? '0' : resultStr;
    resultStr = resultStr.trim();

    return resultStr + "원";
}

// 네 자리마다 돈 단위 추가 (축소 버전)
function priceWonShortenFormatter(value) {
    if(!isNumber(value))
        return value;

    value = Number(value);
    var priceStr = String(value);
    var priceUnits = ['', '만', '억', '조', '경', '해'];

    var repeatCount = 0;
    while(priceStr.length > 4) {
        repeatCount++;
        priceStr = priceStr.substring(0, priceStr.length - 4);
    }

    return addComma(priceStr) + priceUnits[repeatCount] + '원';
}

// 바이트 단위 변환
function byteFormatter(value) {
    if(!isNumber(value))
        return value;

    value = Number(value);
    var units = ['Bytes', 'KB', 'MB', 'GB', 'TB', 'PB'];

    if(value == 0) {
        return 0 + units[0];
    }

    var index = Math.floor(Math.log(value) / Math.log(1024));
    return (value / Math.pow(1024, index)).toFixed(0) + units[index];
}

/* file */

// 파일 용량 제한
function isSavableSize(fileSize, maximum) {
    if(fileSize > maximum)
        return false;
    else
        return true;
}

// 파일 확장자 제한
function isSavableExtension(fileName) {
    var extension = fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length).toLowerCase();
    var savableExtensions = ['jpg', 'jpeg', 'gif', 'png', 'svg', 'hwp', 'doc', 'docx', 'pdf', 'xls', 'xlsx', 'pptx', 'txt'];

    if(savableExtensions.indexOf(extension) > -1)
        return true;
    else
        return false;
}

// 이미지 파일 확장자 제한
function isSavableImageExtension(fileName) {
    var extension = fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length).toLowerCase();
    var savableExtensions = ['jpg', 'jpeg', 'gif', 'png', 'svg'];

    if(savableExtensions.indexOf(extension) > -1)
        return true;
    else
        return false;
}

// 파일 목록 개수 검사
function validateFileCount(elementIdForSelectFile, elementIdForUploadFile, maximumCount) {
    var selectedFileCount = document.getElementById(elementIdForSelectFile).files.length;
    var savedFileCount = document.getElementById(elementIdForUploadFile).files.length;

    if(selectedFileCount == 0) {
        return false;
    }

    if(savedFileCount == maximumCount) {
        openAlertModal("첨부파일을 모두 선택하였습니다.");
        return false;
    }

    if(selectedFileCount > maximumCount) {
        openAlertModal("첨부파일을 " + maximumCount + "개 이하로 선택해주시기 바랍니다.");
        return false;
    }

    if(selectedFileCount + savedFileCount > maximumCount) {
        openAlertModal("첨부파일을 " + (maximumCount - savedFileCount)  + "개 이하로 선택해주시기 바랍니다.");
        return false;
    }

    return true;
}

// input 요소에 파일 목록 저장
function saveFileForUpload(elementIdForSelectFile, elementIdForUploadFile, maximumFileSize, fileType) {
    var selectedFiles = document.getElementById(elementIdForSelectFile).files;
    var selectedFileArray = Array.from(selectedFiles);

    var uploadFiles = document.getElementById(elementIdForUploadFile).files;
    var uploadFilesArray = Array.from(uploadFiles);

    var sizeAllAllowed = true;
    var extensionAllAllowed = true;

    for(var i=selectedFileArray.length ; i>0 ; i--) {
        var file = selectedFileArray[i - 1];

        if(sizeAllAllowed)
            sizeAllAllowed = isSavableSize(file.size, maximumFileSize);

        if(extensionAllAllowed) {
            if(isEmpty(fileType) || fileType == 'all')
                extensionAllAllowed = isSavableExtension(file.name);
            else if(fileType == 'image')
                extensionAllAllowed = isSavableImageExtension(file.name);
        }
    }

    // TODO 수정
    if(!sizeAllAllowed && !extensionAllAllowed) {
        openAlertModal("첨부 불가능한 확장자가 포함되어 있습니다.\n(" + byteFormatter(maximumFileSize) + "이하의 파일만 추가 가능합니다.)");
        return;
    } else if(!sizeAllAllowed) {
        openAlertModal(byteFormatter(maximumFileSize) + "이하의 파일만 추가 가능합니다.");
        return;
    } else if(!extensionAllAllowed) {
        openAlertModal("첨부 불가능한 확장자가 포함되어 있습니다.");
        return;
    }

    var dataTransfer = new DataTransfer();
    selectedFileArray.forEach(file => { uploadFilesArray.push(file) });
    uploadFilesArray.forEach(file => { dataTransfer.items.add(file) });
    document.getElementById(elementIdForUploadFile).files = dataTransfer.files;
}

// input 요소에서 파일 1개 제거
function deleteFileFromInput(elementId, fileIndex) {
    var files = document.getElementById(elementId).files;
    var fileArray = Array.from(files);
    fileArray.splice(fileIndex, 1);

    var dataTransfer = new DataTransfer();
    fileArray.forEach(file => {dataTransfer.items.add(file)});
    document.getElementById(elementId).files = dataTransfer.files;
}


// validate
// checkAll
// uncheckAll
// openPopup
// closePopup
// 주민번호 formatter
// 전화번호 formatter
// 시간 formatter
// 사업자번호 formatter
// 메일주소 유효성 검사
// 종성 확인
// 은/는 을/를 이/가 와/과
// addMinute
// addHour
// addDate
// addMonth
// getDateTime(YYYYMMDD / YYYYMMDDHH24MISS)
// 날짜 formatter (String → YYYY-MM-DD / YYYY/MM/DD / YYYY년 MM월 DD일)
// 날짜 formatter (Date 객체 → YYYY-MM-DD / YYYY/MM/DD / YYYY년 MM월 DD일)
// 날짜 유효성 검사
// from to 날짜 유효성 검사
// from to 날짜 계산
// 요일 계산
// 월의 마지막 날짜 반환