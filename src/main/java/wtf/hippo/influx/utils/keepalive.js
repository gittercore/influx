const http = require('http');
const server = http.createServer((req, res) => {
	res.writeHead(200);
	res.end('bot is up');
});
server.listen(3000);
console.log("keep alive on");