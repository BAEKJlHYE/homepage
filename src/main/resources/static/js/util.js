/* etc */

function isEmpty(value) {
    if ((typeof value === "undefined") || (value === null) || (value === ""))
        return true;
    else
        return false;
}

function copyToClipboard(value) {
    window.navigator.clipboard.writeText(value);
}

/* string */

function lpad(value, length, text) {
    return value.padStart(length, text);
}

function rpad(value, length, text) {
    return value.padEnd(length, text);
}

/* number */

function isNumber(value) {
    if(isNaN(value))
        return false;
    else
        return true;
}

function isOdd(value) {
    if(!isNumber(value))
        return false;

    value = Number(value);

    if(value % 2 == 1)
        return true;
    else
        return false;
}

function isEven(value) {
    if(!isNumber(value))
        return false;

    value = Number(value);

    if(value % 2 == 0)
        return true;
    else
        return false;
}

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

function priceDollarFormatter(value) {
    if(!isNumber(value))
        return value;

    return "$" + addComma(value);
}

function priceWonFormatter(value) {
    if(!isNumber(value))
        return value;

    return addComma(value) + "원";
}

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

function isSavableSize(fileSize, maximum) {
    if(fileSize > maximum)
        return false;
    else
        return true;
}

function isSavableExtension(fileName) {
    var extension = fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length).toLowerCase();
    var savableExtensions = ['jpg', 'jpeg', 'gif', 'png', 'svg', 'hwp', 'doc', 'docx', 'pdf', 'xls', 'xlsx', 'pptx', 'txt'];

    if(savableExtensions.indexOf(extension) > -1)
        return true;
    else
        return false;
}

function isSavableImageExtension(fileName) {
    var extension = fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length).toLowerCase();
    var savableExtensions = ['jpg', 'jpeg', 'gif', 'png', 'svg'];

    if(savableExtensions.indexOf(extension) > -1)
        return true;
    else
        return false;
}



// alert
// confirm
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