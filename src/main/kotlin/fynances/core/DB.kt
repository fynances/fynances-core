package fynances.core

import com.couchbase.lite.*
import java.io.File
import java.util.logging.Logger

/**
 * @author Andrey Yevseyenko
 */
open class DB(path: String, dbName: String) {
    private val manager : Manager = Manager(object : JavaContext(){
        override fun getFilesDir(): File = File(path)
    }, Manager.DEFAULT_OPTIONS)
    private val database: Database = manager.getDatabase(dbName)
    private val log = Logger.getLogger(DB::class.simpleName)
}
