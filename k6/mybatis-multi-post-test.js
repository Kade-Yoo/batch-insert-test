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
    const url = 'http://localhost:8080/order/mybatis-multi-save';
    const payload = JSON.stringify(jsonObj);
    http.post(url, payload, params);
}

/**
 *  █ setup
 *
 *      data_received..................: 97 MB  3.2 MB/s
 *      data_sent......................: 176 MB 5.9 MB/s
 *      http_req_blocked...............: avg=1.92µs   min=0s       med=0s       max=7.4ms   p(90)=1µs      p(95)=1µs
 *      http_req_connecting............: avg=1.13µs   min=0s       med=0s       max=1.05ms  p(90)=0s       p(95)=0s
 *      http_req_duration..............: avg=366.01µs min=90µs     med=293µs    max=98.15ms p(90)=526µs    p(95)=719µs
 *        { expected_response:true }...: avg=366.01µs min=90µs     med=293µs    max=98.15ms p(90)=526µs    p(95)=719µs
 *      http_req_failed................: 0.00%  ✓ 0            ✗ 773615
 *      http_req_receiving.............: avg=19.45µs  min=3µs      med=8µs      max=76.38ms p(90)=35µs     p(95)=55µs
 *      http_req_sending...............: avg=3.24µs   min=1µs      med=2µs      max=20.69ms p(90)=4µs      p(95)=6µs
 *      http_req_tls_handshaking.......: avg=0s       min=0s       med=0s       max=0s      p(90)=0s       p(95)=0s
 *      http_req_waiting...............: avg=343.31µs min=77µs     med=276µs    max=96.68ms p(90)=498µs    p(95)=679µs
 *      http_reqs......................: 773615 25780.784204/s
 *      iteration_duration.............: avg=385.15µs min=102.37µs med=310.04µs max=99.45ms p(90)=548.58µs p(95)=744.71µs
 *      iterations.....................: 773615 25780.784204/s
 *      vus............................: 10     min=10         max=10
 *      vus_max........................: 10     min=10         max=10
 */