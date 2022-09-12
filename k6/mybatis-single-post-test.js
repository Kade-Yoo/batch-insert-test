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
    const url = 'http://localhost:8080/order/mybatis-single-save';
    const payload = JSON.stringify(jsonObj);
    http.post(url, payload, params);
}

/**
 *  █ setup
 *
 *      data_received..................: 97 MB  3.2 MB/s
 *      data_sent......................: 176 MB 5.9 MB/s
 *      http_req_blocked...............: avg=1.98µs   min=0s       med=0s       max=4.39ms  p(90)=1µs      p(95)=1µs
 *      http_req_connecting............: avg=1.19µs   min=0s       med=0s       max=4.36ms  p(90)=0s       p(95)=0s
 *      http_req_duration..............: avg=364.9µs  min=89µs     med=297µs    max=91.1ms  p(90)=537µs    p(95)=727µs
 *        { expected_response:true }...: avg=364.9µs  min=89µs     med=297µs    max=91.1ms  p(90)=537µs    p(95)=727µs
 *      http_req_failed................: 0.00%  ✓ 0            ✗ 776740
 *      http_req_receiving.............: avg=18.59µs  min=-45000ns med=8µs      max=25.28ms p(90)=35µs     p(95)=55µs
 *      http_req_sending...............: avg=3.22µs   min=1µs      med=2µs      max=9.59ms  p(90)=4µs      p(95)=7µs
 *      http_req_tls_handshaking.......: avg=0s       min=0s       med=0s       max=0s      p(90)=0s       p(95)=0s
 *      http_req_waiting...............: avg=343.09µs min=77µs     med=280µs    max=89.62ms p(90)=509µs    p(95)=687µs
 *      http_reqs......................: 776740 25884.624039/s
 *      iteration_duration.............: avg=383.67µs min=103.04µs med=314.08µs max=92.06ms p(90)=559.95µs p(95)=751.95µs
 *      iterations.....................: 776740 25884.624039/s
 *      vus............................: 10     min=10         max=10
 *      vus_max........................: 10     min=10         max=10
 */