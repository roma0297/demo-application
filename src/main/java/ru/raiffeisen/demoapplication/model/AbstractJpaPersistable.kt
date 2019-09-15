package ru.raiffeisen.demoapplication.model

import org.springframework.data.util.ProxyUtils
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass
import java.util.Objects

@MappedSuperclass
abstract class AbstractJpaPersistable<T> {

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

	override fun hashCode(): Int = Objects.hash(getId())

	override fun toString() = "Entity of type ${javaClass.name} with id: $id"
}
