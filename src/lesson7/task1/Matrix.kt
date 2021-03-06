@file:Suppress("UNUSED_PARAMETER", "unused")

package lesson7.task1

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E

    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)

    operator fun set(cell: Cell, value: E)
}

/**
 * Простая
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> {
    val matrix = MatrixImpl<E>(height, width)
    if (width <= 0 || height <= 0) throw IllegalArgumentException()
    for (row in 0..height - 1)
        for (colomn in 0..width - 1)
            matrix[row, colomn] = e
    return matrix
}

/**
 * Средняя сложность
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(override val height: Int, override val width: Int) : Matrix<E> {
    private val listTable = mutableMapOf<Cell, E>()

    override fun get(row: Int, column: Int): E {
        val res = listTable[Cell(row, column)]
        if (res == null) throw  IllegalArgumentException()
        else return res
    }

    override fun get(cell: Cell): E = get(cell.row, cell.column)

    override fun set(row: Int, column: Int, value: E) {
        listTable[Cell(row, column)] = value
    }


    override fun set(cell: Cell, value: E) {
        set(cell.row, cell.column, value)
    }

    override fun equals(other: Any?) = other is MatrixImpl<*> && height == other.height && width == other.width && listTable == other.listTable

    override fun toString(): String {
        var res = "["
        for (row in 0..height - 1) {
            res += "["
            for (col in 0..width - 1) {
                res += this[row, col]
                if (col != width - 1) res += ","
            }
            res += "]"
            if (row != height - 1) res += ","
        }
        return res + "]"
    }
}
