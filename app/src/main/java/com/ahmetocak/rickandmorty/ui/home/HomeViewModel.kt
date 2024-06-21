package com.ahmetocak.rickandmorty.ui.home

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ahmetocak.rickandmorty.R
import com.ahmetocak.rickandmorty.common.Response
import com.ahmetocak.rickandmorty.domain.model.character.Character
import com.ahmetocak.rickandmorty.domain.model.location.Locations
import com.ahmetocak.rickandmorty.domain.usecase.GetCharacterUseCase
import com.ahmetocak.rickandmorty.domain.usecase.GetLocationsUseCase
import com.ahmetocak.rickandmorty.ui.home.utils.LocationNames
import com.ahmetocak.rickandmorty.utils.CharacterGender
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase,
    getLocationsUseCase: GetLocationsUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(
                locationList = getLocationsUseCase().cachedIn(viewModelScope)
            )
        }
    }

    fun getCharacters(residents: List<String>) {
        viewModelScope.launch(ioDispatcher) {
            _uiState.update { it.copy(isLoading = true, characterList = emptyList()) }
            val characterIds = arrayListOf<Int>()

            // In order to get the characters from the API, we extract the character ids from the residents URL.
            residents.forEach { resident ->
                try {
                    characterIds.add(extractCharacterId(resident))
                } catch (e: Exception) {
                    _uiState.update {
                        it.copy(errorMessages = listOf(e.message ?: "Something went wrong!"))
                    }
                }
            }

            // If characterIds is empty, the selected location does not have a resident.
            // If the characterId list is empty, we give an empty arrayList as data.
            // In Home Screen, it will be checked whether this arrayList is empty and a UI will be drawn accordingly.
            if (characterIds.isEmpty()) {
                _uiState.update { it.copy(isLoading = false, characterList = emptyList()) }
            } else {
                when (val response = getCharacterUseCase(characterIds)) {
                    is Response.Success -> {
                        _uiState.update {
                            it.copy(isLoading = false, characterList = response.data)
                        }
                    }

                    is Response.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                characterList = emptyList(),
                                errorMessages = listOf(response.message)
                            )
                        }
                    }
                }
            }
        }
    }

    /**
     * This function extract character id from resident url.
     * @param residentUrl character url.
     * @return character id. If id is null then throw exception.
     */
    private fun extractCharacterId(residentUrl: String): Int {
        val uri = Uri.parse(residentUrl)
        return uri.lastPathSegment?.toInt() ?: throw Exception()
    }

    fun consumedErrorMessage(message: String) {
        _uiState.update {
            it.copy(errorMessages = it.errorMessages.filterNot { errorMessage ->
                errorMessage == message
            })
        }
    }

    /**
     * This function return a gender image that indicates character gender.
     * @param gender character gender.
     * @return gender image.
     */
    fun setCharacterGenderImage(gender: String): Int {
        return when (gender) {
            CharacterGender.MALE -> {
                R.drawable.male
            }

            CharacterGender.FEMALE -> {
                R.drawable.female
            }

            CharacterGender.GENDERLESS -> {
                R.drawable.genderless
            }

            CharacterGender.UNKNOWN -> {
                R.drawable.unknown
            }

            else -> {
                R.drawable.unknown
            }
        }
    }

    /**
     * This function matches the location name from the API with the location images in the
     * resources and returns the matching location image.
     * @param locationName from API.
     * @return The location image that matches the location name. If there is no match, the function
     * returns an image indicating that there is no image available.
     */
    fun setLocationImage(locationName: String): Int {
        return when (locationName) {
            LocationNames.earth_5_126 -> {
                R.drawable.earth_c_137
            }

            LocationNames.abadango -> {
                R.drawable.abadango
            }

            LocationNames.citadel_of_ricks -> {
                R.drawable.citadel_of_ricks
            }

            LocationNames.anatomy_park -> {
                R.drawable.anatomy_park
            }

            LocationNames.bepis_nine -> {
                R.drawable.bepis_9
            }

            LocationNames.bird_world -> {
                R.drawable.bird_world
            }

            LocationNames.earth_c137 -> {
                R.drawable.earth_c_137
            }

            LocationNames.gromflom_prime -> {
                R.drawable.gromflom_prime
            }

            LocationNames.giants_town -> {
                R.drawable.giant_town
            }

            LocationNames.cronenberg_earth -> {
                R.drawable.cronenberg_earth
            }

            LocationNames.earth_replacement_dimension -> {
                R.drawable.earth_replacement_dimension_
            }

            LocationNames.wordlenders_lair -> {
                R.drawable.worldenders_lair
            }

            LocationNames.interdimensional_cable -> {
                R.drawable.interdimensional
            }

            LocationNames.immortality_field_resort -> {
                R.drawable.immortality_field_resort
            }

            LocationNames.post_apocalyptic_earth -> {
                R.drawable.post_apocalyptic_world
            }

            LocationNames.purge_planet -> {
                R.drawable.purge_planet
            }

            LocationNames.venezunalon_seven -> {
                R.drawable.venzenulon_7
            }

            LocationNames.nuptia_four -> {
                R.drawable.interdimensional
            }

            LocationNames.st_gloopy_noops_hospital -> {
                R.drawable.st_gloopy_noops_hospital
            }

            LocationNames.mr_goldenfolds_dream -> {
                R.drawable.mr_goldenfolds_dream
            }

            LocationNames.earth_evil_ricks_target_dimension -> {
                R.drawable.earth_evil_ricks_target_dimension
            }

            LocationNames.earth_j_197 -> {
                R.drawable.earth_j197
            }

            LocationNames.eric_stoltz_mask_earth -> {
                R.drawable.eric_stoltz_mask_earth
            }

            LocationNames.galactic_federation_prison -> {
                R.drawable.galactic_federation_prison
            }

            LocationNames.gazorpazorp -> {
                R.drawable.gazorpazorp
            }

            LocationNames.glaagablaaga -> {
                R.drawable.glaagablaaga
            }

            LocationNames.interdimensional_customs -> {
                R.drawable.interdimensional_customs
            }

            LocationNames.planet_squanch -> {
                R.drawable.planet_squanch
            }

            LocationNames.resort_planet -> {
                R.drawable.resort_planet
            }

            LocationNames.roy_a_life_well_lived -> {
                R.drawable.roy_a_life_well_lived
            }

            LocationNames.earth_unknwon_dimension -> {
                R.drawable.dimension_earth_unknown
            }

            LocationNames.dorian_five -> {
                R.drawable.dorian_five
            }

            LocationNames.earth_c_500_a -> {
                R.drawable.earrh_c_500_a
            }

            LocationNames.earth_k_83 -> {
                R.drawable.earth_k_83
            }

            LocationNames.hideout_planet -> {
                R.drawable.hideout_planet
            }

            LocationNames.ricks_battery_microserve -> {
                R.drawable.ricks_battery_microverse
            }

            LocationNames.signus_five_expanse -> {
                R.drawable.signus_five_expanse
            }

            LocationNames.testicle_monster_dimension -> {
                R.drawable.testicle_monster_dimension
            }

            LocationNames.the_menageri -> {
                R.drawable.the_menagerie
            }

            LocationNames.unitys_planet -> {
                R.drawable.unitys_planet
            }

            LocationNames.hamster_in_butt_world -> {
                R.drawable.hamster_in_the_butt_world
            }

            LocationNames.alien_day_spa -> {
                R.drawable.alien_day_spa
            }

            LocationNames.alphabetrium -> {
                R.drawable.alphabetrium
            }

            LocationNames.arboles_mentirosos -> {
                R.drawable.arboles_mentirosos
            }

            LocationNames.blips_and_chitz -> {
                R.drawable.blips_and_chitz
            }

            LocationNames.detoxifier -> {
                R.drawable.detoxifier
            }

            LocationNames.earth_c35 -> {
                R.drawable.earth_c_35
            }

            LocationNames.earth_chair_dimension -> {
                R.drawable.earth_chair_dimension
            }

            LocationNames.earth_d716 -> {
                R.drawable.earth_d716
            }

            LocationNames.earth_d716_b -> {
                R.drawable.earth_d716_b
            }

            LocationNames.earth_d715_c -> {
                R.drawable.earth_d716_c
            }

            LocationNames.earth_d_99 -> {
                R.drawable.earth_d_99
            }

            LocationNames.earth_fascist_dimension -> {
                R.drawable.earth_fascist_dimension
            }

            LocationNames.earth_fascist_shrimp_dimension -> {
                R.drawable.earth_fascist_shrimp_dimension
            }

            LocationNames.earth_fascist_teddy_bear_dimension -> {
                R.drawable.earth_fascist_teddy_bear_dimension
            }

            LocationNames.earth_j22 -> {
                R.drawable.earth_k_22
            }

            LocationNames.earth_phone_dimension -> {
                R.drawable.earth_phone_dimension
            }

            LocationNames.earth_pizza_dimension -> {
                R.drawable.earth_pizza_dimension
            }

            LocationNames.earth_wasp_dimension -> {
                R.drawable.earth_wasp_dimension
            }

            LocationNames.forbodulon_prime -> {
                R.drawable.forbodulon_prime
            }

            LocationNames.froopyland -> {
                R.drawable.froopyland
            }

            LocationNames.gear_world -> {
                R.drawable.gear_world
            }

            LocationNames.girvonesk -> {
                R.drawable.girvonesk
            }

            LocationNames.greasy_grandma_world -> {
                R.drawable.greasy_grandma_world
            }

            LocationNames.jerryboree -> {
                R.drawable.jerryboree
            }

            LocationNames.krootabulon -> {
                R.drawable.krootabulon
            }

            LocationNames.kyles_teenyverse -> {
                R.drawable.kyles_teenyverse
            }

            LocationNames.larva_alien_planet -> {
                R.drawable.larva_aliens_planet
            }

            LocationNames.mega_gargantuan_kingdom -> {
                R.drawable.mega_gargantuan_kingdom
            }

            LocationNames.mr_meeseeks_book -> {
                R.drawable.mr_meeseeks_box
            }

            LocationNames.pawn_shop_planet -> {
                R.drawable.pawn_shop_planet
            }

            LocationNames.plopstar -> {
                R.drawable.plopstar
            }

            LocationNames.pluto -> {
                R.drawable.pluto
            }

            LocationNames.snake_planet -> {
                R.drawable.snake_planet
            }

            LocationNames.snuffles_dream -> {
                R.drawable.snuffles_dream
            }

            LocationNames.earth_giant_telepathic_spiders -> {
                R.drawable.telepathic_spider
            }

            LocationNames.trunk_world -> {
                R.drawable.trunk_world
            }

            LocationNames.vindicators_base -> {
                R.drawable.vindicators_base
            }

            LocationNames.zeep_xanflorps_miniverse -> {
                R.drawable.zeep_xanflorps_miniverse
            }

            LocationNames.zigerions_base -> {
                R.drawable.zigerions_base
            }

            LocationNames.monogatron_mothership -> {
                R.drawable.monogatron_mothership
            }

            LocationNames.gordon_quadrant -> {
                R.drawable.gorgon_quadrant
            }

            LocationNames.midland_quasar -> {
                R.drawable.midland_quasar
            }

            LocationNames.mount_space_everest -> {
                R.drawable.mount_space_everest
            }

            LocationNames.globalflyn -> {
                R.drawable.globaflyn
            }

            LocationNames.heist_con -> {
                R.drawable.heist_con
            }

            LocationNames.heistotron -> {
                R.drawable.heistotron_base
            }

            LocationNames.mount_olympus -> {
                R.drawable.mount_olympus
            }

            LocationNames.plitzville_montana -> {
                R.drawable.plitzville_montana
            }

            LocationNames.earth_tusk_dimension -> {
                R.drawable.earth_tusk_dimension
            }

            LocationNames.gramuflack -> {
                R.drawable.gramuflack
            }

            LocationNames.new_improved_galactic_federation -> {
                R.drawable.new_improved_galactic_federation_quarters
            }

            LocationNames.story_train -> {
                R.drawable.story_train
            }

            LocationNames.non_diegetic_alternative_reality -> {
                R.drawable.non_diegetic_alternative_reality
            }

            LocationNames.tickets_please_guy_nightmare -> {
                R.drawable.tickets_please_guy_nightmare
            }

            LocationNames.mortys_story -> {
                R.drawable.mortys_story
            }

            LocationNames.ricks_story -> {
                R.drawable.rickss_story
            }

            LocationNames.ricks_memories -> {
                R.drawable.ricks_memories
            }

            LocationNames.rick_and_two_crows_planet -> {
                R.drawable.rick_and_two_crows_planet
            }

            LocationNames.normal_size_bug_dimension -> {
                R.drawable.normal_size_bug_dimension
            }

            LocationNames.slartivart -> {
                R.drawable.slartivart
            }

            LocationNames.avian_planet -> {
                R.drawable.avian_planet
            }

            LocationNames.ricks_consciousness -> {
                R.drawable.ricks_consciousness
            }

            LocationNames.birdpersons_consciousness -> {
                R.drawable.birdpersons_consciousness
            }

            LocationNames.france -> {
                R.drawable.france
            }

            LocationNames.space_tahoe -> {
                R.drawable.space_tahoe
            }

            LocationNames.z_q_p_d -> {
                R.drawable.z_q_p_d
            }

            LocationNames.hell -> {
                R.drawable.hell
            }

            LocationNames.space -> {
                R.drawable.space
            }

            LocationNames.morty -> {
                R.drawable.morty
            }

            LocationNames.ferkus_nine -> {
                R.drawable.ferkus_9
            }

            LocationNames.morglutz -> {
                R.drawable.morglutz
            }

            LocationNames.elemental_rings -> {
                R.drawable.elemental_rings
            }

            LocationNames.narnia_dimension -> {
                R.drawable.narnia_dimension
            }

            LocationNames.the_ocean -> {
                R.drawable.the_ocean
            }

            LocationNames.defiances_ship -> {
                R.drawable.defiance_ship
            }

            LocationNames.defiance_base -> {
                R.drawable.defiances_base
            }

            LocationNames.gaia -> {
                R.drawable.gaia
            }

            LocationNames.nx_five_planet_remover -> {
                R.drawable.nx_five_planet_remover
            }

            LocationNames.near_duplicate_reality -> {
                R.drawable.near_duplicate_reality
            }

            LocationNames.merged_universe -> {
                R.drawable.merged_universe
            }

            LocationNames.alien_acid_plant -> {
                R.drawable.alien_acid_plant
            }

            LocationNames.glorzo_asteroid -> {
                R.drawable.glorzo_asteroid
            }

            LocationNames.fantasy_world -> {
                R.drawable.fantasy_world
            }

            LocationNames.earth_k22 -> {
                R.drawable.earth_k_22
            }

            LocationNames.draygon -> {
                R.drawable.draygon
            }

            else -> {
                R.drawable.no_image_available
            }
        }
    }
}

data class HomeUiState(
    val isLoading: Boolean = true,
    val errorMessages: List<String> = emptyList(),
    val locationList: Flow<PagingData<Locations>> = emptyFlow(),
    val characterList: List<Character> = emptyList()
)