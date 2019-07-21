import interop.godot_method_bind
import interop.godot_method_bind_get_method
import kotlinx.cinterop.CPointer

object BindMap {
    private val content =  mutableMapOf<String, CPointer<godot_method_bind>>()

    private fun makeBind(key: String): CPointer<godot_method_bind> {
        val sets = key.split("::")

        content[key] = godot_method_bind_get_method(sets[0], sets[1])
                ?: throw Exception("Cannot create a method bind to '$key'!")

        return content[key]!!
    }

    operator fun get(key: String): CPointer<godot_method_bind> {
        return content[key] ?: makeBind(key)
    }

}

