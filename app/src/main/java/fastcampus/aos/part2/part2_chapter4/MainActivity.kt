package fastcampus.aos.part2.part2_chapter4

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import fastcampus.aos.part2.part2_chapter4.adapter.UserAdapter
import fastcampus.aos.part2.part2_chapter4.databinding.ActivityMainBinding
import fastcampus.aos.part2.part2_chapter4.model.Repo
import fastcampus.aos.part2.part2_chapter4.model.UserDto
import fastcampus.aos.part2.part2_chapter4.network.GithubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val githubService = retrofit.create(GithubService::class.java)
        githubService.listRepos("square").enqueue(object: Callback<List<Repo>> {
            override fun onResponse(call: Call<List<Repo>?>, response: Response<List<Repo>?>) {
                Log.e("MainActivity", "List Repo : ${response.body()}")
            }

            override fun onFailure(call: Call<List<Repo>?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        val userAdapter = UserAdapter()
        binding.userRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter
        }

        githubService.searchUsers("squar").enqueue(object : Callback<UserDto> {
            override fun onResponse(call: Call<UserDto?>, response: Response<UserDto?>) {
                Log.e("MainActivity", "Search User : ${response.body()}")

                userAdapter.submitList(response.body()?.items?: emptyList())
            }

            override fun onFailure(call: Call<UserDto?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }
}