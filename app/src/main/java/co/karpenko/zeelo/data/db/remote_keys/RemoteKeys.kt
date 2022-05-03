package co.karpenko.zeelo.data.db.remote_keys

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Alexander Karpenko on 2/5/22.
 * java.karpenko@gmail.com
 */
@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey
    val repoId: Int,
    val prevKey: Int?,
    val nextKey: Int?
)
