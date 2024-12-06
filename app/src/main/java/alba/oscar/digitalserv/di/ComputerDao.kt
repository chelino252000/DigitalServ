package alba.oscar.digitalserv.di

import alba.oscar.digitalserv.MySqlConexion
import alba.oscar.digitalserv.domain.model.ComputerModel
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object ComputerDao {
    fun listar(dato: String): List<ComputerModel>{
        var lista = mutableListOf<ComputerModel>()

        val ps =  MySqlConexion.getConexion().prepareStatement(
            "SELECT id_pc, num_serial, nombre, marca, modelo,  periodicidad_mant FROM computadoras WHERE nombre LIKE concat('%','?','%');"
        )


        ps.setString(1, dato)

        val rs = ps.executeQuery()

        while (rs.next()) {
            lista.add(
                ComputerModel(
                    rs.getInt("id_pc"),
                    rs.getInt("num_serial"),
                    rs.getString("nombre"),
                    rs.getString("marca"),
                    rs.getString("modelo"),

                    rs.getInt("periodicidad_mant")
                )
            )
        }

        rs.close()
        ps.close()

        return lista

    }

    private fun registrar(computer: ComputerModel) {
        val ps = MySqlConexion.getConexion().prepareStatement(
            "INSERT INTO computadoras (num_serial, nombre, marca, modelo,  peridiocidad_mant) VALUES (?, ?, ?, ?, ?)"
        )

        ps.setInt(1, computer.num_serial)
        ps.setString(2, computer.nombre)
        ps.setString(3, computer.marca)
        ps.setString(4, computer.modelo)
        ps.setInt(5, computer.periodicidad_mant)

        ps.executeUpdate()

        ps.close()
    }


    private fun actualizar(computer: ComputerModel) {
        val ps = MySqlConexion.getConexion().prepareStatement(
            "UPDATE computadoras SET num_serial=?, nombre=?, marca=?, modelo=?, peridiocidad_mant=?"
        )

        ps.setInt(1, computer.num_serial)
        ps.setString(2, computer.nombre)
        ps.setString(3, computer.marca)
        ps.setString(4, computer.modelo)
        ps.setInt(5, computer.periodicidad_mant)
        ps.setInt(6, computer.id_pc)

        ps.executeUpdate()

        ps.close()
    }

    fun eliminar(computer: ComputerModel) {
        val ps = MySqlConexion.getConexion().prepareStatement(
            "DELETE FROM computadoras WHERE id_pc=?"
        )

        ps.setInt(1, computer.id_pc)

        ps.executeUpdate()

        ps.close()
    }

    fun grabar(computer: ComputerModel){
        if (computer.id_pc==0){
            registrar(computer)
        } else {
            actualizar(computer)
        }
    }
}