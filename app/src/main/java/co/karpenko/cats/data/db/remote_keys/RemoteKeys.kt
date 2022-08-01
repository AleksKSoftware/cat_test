package co.karpenko.cats.data.db.remote_keys

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Alexander Karpenko on 30/07/22.
 * java.karpenko@gmail.com
 */
@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey
    val repoId: String,
    val prevKey: Int?,
    val nextKey: Int?
)
