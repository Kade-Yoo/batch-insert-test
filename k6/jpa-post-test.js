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
  duration: '30s'
};

let jsonObj = [];
export function setup() {
  for (let idx = 0; idx < 10000; idx++) {
    jsonObj.push({productNm: 'Product' + idx + 3, orderType: 'COMPLETE'});
  }
}

export default function () {
  const url = 'http://localhost:8080/order/jpa-save';
  const payload = JSON.stringify(jsonObj);
  http.post(url, payload, params);
}

/**
 *      █ setup
 *      data_received..................: 92 MB  3.1 MB/s
 *      data_sent......................: 159 MB 5.3 MB/s
 *      http_req_blocked...............: avg=1.99µs   min=0s       med=0s       max=5.44ms   p(90)=1µs   p(95)=1µs
 *      http_req_connecting............: avg=1.19µs   min=0s       med=0s       max=1.65ms   p(90)=0s    p(95)=0s
 *      http_req_duration..............: avg=385.26µs min=103µs    med=297µs    max=107.36ms p(90)=567µs p(95)=808µs
 *        { expected_response:true }...: avg=385.26µs min=103µs    med=297µs    max=107.36ms p(90)=567µs p(95)=808µs
 *      http_req_failed................: 0.00%  ✓ 0            ✗ 737595
 *      http_req_receiving.............: avg=21.27µs  min=3µs      med=10µs     max=34.43ms  p(90)=38µs  p(95)=60µs
 *      http_req_sending...............: avg=3.18µs   min=1µs      med=2µs      max=5.03ms   p(90)=4µs   p(95)=7µs
 *      http_req_tls_handshaking.......: avg=0s       min=0s       med=0s       max=0s       p(90)=0s    p(95)=0s
 *      http_req_waiting...............: avg=360.8µs  min=87µs     med=279µs    max=105.9ms  p(90)=535µs p(95)=758µs
 *      http_reqs......................: 737595 24578.484956/s
 *      iteration_duration.............: avg=404.14µs min=114.83µs med=315.33µs max=108.31ms p(90)=590µs p(95)=832.62µs
 *      iterations.....................: 737595 24578.484956/s
 *      vus............................: 10     min=10         max=10
 *      vus_max........................: 10     min=10         max=10    source=console
 */