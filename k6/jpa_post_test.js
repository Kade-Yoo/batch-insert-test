import http from 'k6/http';

export default function () {
  const url = 'http://localhost:8080/order/jpa-save';
  let jsonObj = [];
  jsonObj.push({productNm: '123', orderType: 'COMPLETE'});
  jsonObj.push({productNm: '1234', orderType: 'FAIL'});

  const payload = JSON.stringify(jsonObj);
  const params = {
    headers: {
      'Content-Type': 'application/json;charset=UTF-8',
	  'Accept': 'application/json',
	  'X-Forwarded-For': '127.0.0.1'
    },
  };

  http.post(url, payload, params);
}