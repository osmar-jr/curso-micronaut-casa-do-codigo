package br.zup.com

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("br.zup.com")
		.start()
}

