import http from 'k6/http';

const params = {
  headers: {
    'Content-Type': 'application/json;charset=UTF-8',
    'Accept': 'application/json',
    'X-Forwarded-For': '127.0.0.1'
  },
};

export const options = {
  vus: 10,
  duration: '30s',
};

export default function () {
  const url = 'http://localhost:8080/order/jpa-save';
  let jsonObj = [];
  jsonObj.push({productNm: '123', orderType: 'COMPLETE'});
  jsonObj.push({productNm: '1234', orderType: 'FAIL'});

  const payload = JSON.stringify(jsonObj);
  http.post(url, payload, params);
}