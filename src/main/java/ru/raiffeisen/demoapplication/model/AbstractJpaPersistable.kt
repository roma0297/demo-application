package ru.raiffeisen.demoapplication.model

import org.springframework.data.util.ProxyUtils
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass
import java.io.Serializable

@MappedSuperclass
abstract class AbstractJpaPersistable<T : Serializable> {

	@Id
	@GeneratedValue
	private var id: T? = null

	fun getId(): T? = id

	override fun equals(other: Any?): Boolean {
		other ?: return false

		if (this === other) return true
		if (javaClass != ProxyUtils.getUserClass(other)) return false
		other as AbstractJpaPersistable<*>

		return this.getId() == other.getId() && this.getId() != null
	}

	override fun hashCode(): Int = 31

	override fun toString() = "Entity of type ${javaClass.name} with id: $id"

	companion object {
		@JvmStatic
		private val serialVersionUID = -5554308939380869754L
	}
}
