import http from 'k6/http';

const params = {
    headers: {
        'Content-Type': 'application/json;charset=UTF-8',
        'Accept': 'application/json',
        'X-Forwarded-For': '127.0.0.1'
    },
};

export const options = {
    vus: 1000,
    duration: '30s'
};

let jsonObj = [];
export function setup() {
    for (let idx = 0; idx < 10000; idx++) {
        jsonObj.push({productNm: 'Product' + idx + 3, orderType: 'COMPLETE'});
    }
}

export default function () {
    const url = 'http://localhost:8080/order/jdbc-save';
    const payload = JSON.stringify(jsonObj);
    http.post(url, payload, params);
}

/**
 * vus = 10
 * █ setup
 *
 *      data_received..................: 81 MB  2.7 MB/s
 *      data_sent......................: 140 MB 4.7 MB/s
 *      http_req_blocked...............: avg=2.02µs   min=0s       med=0s    max=2.64ms  p(90)=1µs      p(95)=1µs
 *      http_req_connecting............: avg=1.21µs   min=0s       med=0s    max=1.12ms  p(90)=0s       p(95)=0s
 *      http_req_duration..............: avg=441.52µs min=102µs    med=298µs max=3.49s   p(90)=582µs    p(95)=838µs
 *        { expected_response:true }...: avg=441.52µs min=102µs    med=298µs max=3.49s   p(90)=582µs    p(95)=838µs
 *      http_req_failed................: 0.00%  ✓ 0            ✗ 647135
 *      http_req_receiving.............: avg=21.34µs  min=3µs      med=10µs  max=62.14ms p(90)=38µs     p(95)=61µs
 *      http_req_sending...............: avg=3.24µs   min=1µs      med=2µs   max=5.33ms  p(90)=4µs      p(95)=7µs
 *      http_req_tls_handshaking.......: avg=0s       min=0s       med=0s    max=0s      p(90)=0s       p(95)=0s
 *      http_req_waiting...............: avg=416.93µs min=91µs     med=279µs max=3.49s   p(90)=548µs    p(95)=785µs
 *      http_reqs......................: 647135 21565.711261/s
 *      iteration_duration.............: avg=460.96µs min=114.79µs med=316µs max=3.49s   p(90)=605.66µs p(95)=861.88µs
 *      iterations.....................: 647135 21565.711261/s
 *      vus............................: 10     min=10         max=10
 *      vus_max........................: 10     min=10         max=10
 *
 *
 *
 */

