package alba.oscar.digitalserv.domain.model

data class ComputerModel(
    var id_pc: Int=0,
    var num_serial: Int=0,
    var nombre: String="",
    var marca: String="",
    var modelo: String ="",

    var periodicidad_mant: Int=0
)
