package com.nithin.kotlinmvvm.utils

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.google.common.truth.Truth.assertThat


@RunWith(JUnit4::class)
class UtilsTest{

    @Test
    fun whenToastIsValid(){
        val message = "Some Url"
        val result = Utils.validateToast(message)
        assertThat(result).isEqualTo(true)
    }

}