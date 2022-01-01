package org.tapsell.admediator.data.repository


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.tapsell.admediator.data.local.db.waterfall.WaterfallDao
import org.tapsell.admediator.data.local.db.waterfall.WaterfallEntity
import org.tapsell.admediator.di.DatabaseModule


object LocalWaterfallRepositoryImpl : LocalWaterfallsRepository {

    private val dao: WaterfallDao by lazy {
        DatabaseModule.provideWaterfallDao()
    }


    override suspend fun getAll(): List<WaterfallEntity> = withContext(Dispatchers.IO) {
        dao.getAll()
    }

    override suspend fun getItem(id: String): WaterfallEntity? = withContext(Dispatchers.IO) {
        dao.getItem(id)
    }

    override suspend fun insertAll(list: List<WaterfallEntity>) =
        withContext(Dispatchers.IO) {
            dao.insertAll(list)
        }


    override suspend fun insert(waterfall: WaterfallEntity) = withContext(Dispatchers.IO) {
        dao.insert(waterfall)
    }

    override suspend fun update(waterfall: WaterfallEntity) = withContext(Dispatchers.IO) {
        dao.update(waterfall)
    }

    override suspend fun delete(waterfall: WaterfallEntity) = withContext(Dispatchers.IO) {
        dao.delete(waterfall)
    }

    override suspend fun deleteAll() = withContext(Dispatchers.IO) {
        dao.deleteAll()
    }

}
