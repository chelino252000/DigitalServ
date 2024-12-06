package alba.oscar.digitalserv

import java.sql.Connection
import java.sql.DriverManager

object MySqlConexion {
    fun getConexion(): Connection {
        Class.forName("com.mysql.jdbc.Driver")

        return DriverManager.getConnection(
            "jdbc:mysql://192.168.0.69:3306/digitalservice",
            "root",
            ""
        )
    }
}