package org.tapsell.admediator.data.local.db.waterfall

import android.os.Parcelable

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "waterfall")
data class WaterfallEntity(
    @PrimaryKey
    var id: String,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "timestamp")
    var timestamp: Long
) : Parcelable
