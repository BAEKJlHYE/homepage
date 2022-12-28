function isEmpty(value) {
    if ((typeof value === "undefined") || (value === null) || (value === ""))
        return true;
    else
        return false;
}

/* 숫자 */

function isNumber(value) {
    if(isNaN(value))
        return false;
    else
        return true;
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
        resultStr = resultStr + addComma(priceSplit[i - 1]) + priceUnits[i - 1] + ' ';
    }

    return resultStr.trim() + "원";
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



// alert
// confirm
// validate
// checkAll
// uncheckAll
// copy
// openPopup
// closePopup
// number - formatter(comma + dollar, plus/minus, won)
// number - check odd, even
// number - calculate byte to KB, MB, GB
// 주민번호 formatter
// 전화번호 formatter
// 시간 formatter
// 사업자번호 formatter
// 메일주소 유효성 검사
// 종성 확인
// 은/는 을/를 이/가 와/과
// trim
// lpad, rpad
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
// 시간 formatter
// 파일 확장자 확인