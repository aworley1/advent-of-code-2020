package matchers

import assertk.Assert
import assertk.assertions.isTrue
import com.natpryce.Failure
import com.natpryce.Result
import com.natpryce.Success

fun <R : Result<T, E>, T, E> Assert<R>.isSuccess() = given { actual ->
    assertThat(actual is Success<*>).isTrue()
}

fun <R : Result<T, E>, T, E> Assert<R>.isFailure() = given { actual ->
    assertThat(actual is Failure<*>).isTrue()
}

