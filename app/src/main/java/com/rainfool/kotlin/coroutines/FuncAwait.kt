package com.rainfool.kotlin.coroutines

import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine

/**
 *
 * Created by rainfool on 2019/5/14.
 */

//public suspend fun <T : Any> GetUserProfile.await(): T {
//    return suspendCancellableCoroutine { continuation ->
//        object :GetUserProfile() {
//            override fun onResponse(json: String?) {
//                super.onResponse(json)
//                continuation.resumeWith(runCat)
//            }
//        }.execute()
//    }
//}