package pe.edu.ulima.aprendiendo.helpers

class ApplicationHelper {
    companion object {
        fun randomStringNumber(length: Int): String{
            val charPool: List<Char> = ('0'..'9').toList()
            val randomString = (1..length)
                .map { _ -> kotlin.random.Random.nextInt(0, charPool.size) }
                .map(charPool::get)
                .joinToString("")
            return randomString
        }
    }
}