package com.android.codex.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.codex.others.Resource

class FakeRepository : LocalRepository, RemoteRepository {
    private val fakeList = mutableListOf<Run>()
    private val observableRuns = MutableLiveData<List<Run>>(fakeList)

    private var networkError = false


    fun setNetwork(value:Boolean){
        networkError=value
    }

    fun refreshData() {
        observableRuns.postValue(fakeList)
    }

    override suspend fun insertRun(run: Run) {
        fakeList.add(run)
    }

    override suspend fun deleteRun(run: Run) {
        fakeList.remove(run)
    }

    override fun getAllRunsSortedByDate(): LiveData<List<Run>> {
        return observableRuns
    }

    override fun getAllRunsSortedByDistance(): LiveData<List<Run>> {
        return observableRuns
    }

    override fun getAllRunsSortedByTimeInMillis(): LiveData<List<Run>> {
        return observableRuns
    }

    override fun getAllRunsSortedByAvgSpeed(): LiveData<List<Run>> {
        return observableRuns
    }

    override fun getAllRunsSortedByCaloriesBurned(): LiveData<List<Run>> {
        return observableRuns
    }

    override fun getTotalAvgSpeed(): LiveData<Float> {
        return MutableLiveData()
    }

    override fun getTotalDistance(): LiveData<Int> {
        return MutableLiveData()
    }

    override fun getTotalCaloriesBurned(): LiveData<Int> {
        return MutableLiveData()
    }

    override fun getTotalTimeInMillis(): LiveData<Long> {
        return MutableLiveData()
    }

    override suspend fun searchImage(q: String): Resource<ImageResultResponse> {
       return if (networkError){
            Resource.error("error",null)
        }else{
           Resource.success(ImageResultResponse(listOf(),0,0))
       }
    }

}