package fastcampus.aos.part2.part2_chapter4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fastcampus.aos.part2.part2_chapter4.databinding.ActivityRepoBinding

class RepoActivity: AppCompatActivity() {

    private lateinit var binding: ActivityRepoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRepoBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}