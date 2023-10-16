package top.yumik.core.datastore

import androidx.datastore.core.Serializer
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class UserDetailSerializer @Inject constructor(
) : Serializer<UserDetailProto> {

    override val defaultValue: UserDetailProto = UserDetailProto.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserDetailProto {
        try {
            return UserDetailProto.parseFrom(input)
        } catch (exception: Exception) {
            throw Exception("无法读取 \"UserDetail\" proto：", exception)
        }
    }

    override suspend fun writeTo(t: UserDetailProto, output: OutputStream) {
        t.writeTo(output)
    }
}