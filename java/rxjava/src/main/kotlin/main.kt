import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

fun main() {
    testCreate()
}

private fun testCreate() {
    val disposable: Disposable = Observable.create {
        it.onNext("onNext")
        it.onComplete()
    }
        .observeOn(Schedulers.newThread())
        .subscribeOn(Schedulers.newThread())
        .map {
            it + "1"
        }
        .subscribe(
            {
                println("onSuccess result = $it")
            },
            {
                println("onError")
                it.printStackTrace()
            }
        )

    Thread.sleep(5000)
    disposable.dispose()
}