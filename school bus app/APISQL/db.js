const{ Pool } = requires('pg')
const pool = new Pool({
    host: 'localhost',
    port:5432,
    user: 'postgres',
    password: '123456',
    database: 'Database'
})

module.exports = pool
