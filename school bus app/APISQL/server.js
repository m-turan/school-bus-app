const express = require('express')
const app = express()
const pool = require('/.db')
const port = 5000

app.listen(port, () => console.log("oluyore"))