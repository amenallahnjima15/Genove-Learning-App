package com.genove.learning

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.genove.learning.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _b: FragmentProfileBinding? = null
    private val b get() = _b!!

    private var isOldPasswordVisible = false
    private var isNewPasswordVisible = false

    // 16 3D Clay Avatar options (8 Girls & 8 Boys)
    private val avatarOptions = arrayOf(
        AvatarItem("Girl 1: Lilac Hoodie", R.drawable.img_avatar_girl_1),
        AvatarItem("Girl 2: Mint & Headband", R.drawable.img_avatar_girl_2),
        AvatarItem("Girl 3: Blonde & Glasses", R.drawable.img_avatar_girl_3),
        AvatarItem("Girl 4: Curly & Coral", R.drawable.img_avatar_girl_4),
        AvatarItem("Girl 5: Headphones & Mint", R.drawable.img_avatar_girl_5),
        AvatarItem("Girl 6: Lavender Space Buns", R.drawable.img_avatar_girl_6),
        AvatarItem("Girl 7: Bob & Round Glasses", R.drawable.img_avatar_girl_7),
        AvatarItem("Girl 8: Auburn & Yellow Headband", R.drawable.img_avatar_girl_8),
        AvatarItem("Boy 1: Pastel Purple", R.drawable.img_avatar_boy_1),
        AvatarItem("Boy 2: Curly & Aqua", R.drawable.img_avatar_boy_2),
        AvatarItem("Boy 3: Glasses & Orange", R.drawable.img_avatar_boy_3),
        AvatarItem("Boy 4: Blonde & Navy", R.drawable.img_avatar_boy_4),
        AvatarItem("Boy 5: Afro & Ochre", R.drawable.img_avatar_boy_5),
        AvatarItem("Boy 6: Spiky & Emerald", R.drawable.img_avatar_boy_6),
        AvatarItem("Boy 7: Side Part & Burgundy", R.drawable.img_avatar_boy_7),
        AvatarItem("Boy 8: Buzz Cut & Electric Blue", R.drawable.img_avatar_boy_8)
    )

    data class AvatarItem(val name: String, val resId: Int)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _b = FragmentProfileBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Back button
        b.btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        // Default profile values
        b.etProfileName.setText("Melissa Peters")
        b.etProfileEmail.setText("melpeters@gmail.com")
        b.etProfileOldPassword.setText("••••••••••")
        b.etProfileNewPassword.setText("")

        // 3D Avatar chooser dialog
        val showAvatarChooser = {
            val names = avatarOptions.map { it.name }.toTypedArray()
            AlertDialog.Builder(requireContext())
                .setTitle("Choose a 3D Avatar")
                .setItems(names) { _, which ->
                    val chosen = avatarOptions[which]
                    b.imgAvatar.setImageResource(chosen.resId)
                    Toast.makeText(requireContext(), "Avatar updated!", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Cancel", null)
                .show()
        }

        b.btnChangeAvatarContainer.setOnClickListener { showAvatarChooser() }
        b.btnChangePhoto.setOnClickListener { showAvatarChooser() }

        // Toggle old password visibility
        b.btnToggleOldPassword.setOnClickListener {
            isOldPasswordVisible = !isOldPasswordVisible
            b.etProfileOldPassword.transformationMethod = if (isOldPasswordVisible) {
                HideReturnsTransformationMethod.getInstance()
            } else {
                PasswordTransformationMethod.getInstance()
            }
            b.etProfileOldPassword.setSelection(b.etProfileOldPassword.text?.length ?: 0)
        }

        // Toggle new password visibility
        b.btnToggleNewPassword.setOnClickListener {
            isNewPasswordVisible = !isNewPasswordVisible
            b.etProfileNewPassword.transformationMethod = if (isNewPasswordVisible) {
                HideReturnsTransformationMethod.getInstance()
            } else {
                PasswordTransformationMethod.getInstance()
            }
            b.etProfileNewPassword.setSelection(b.etProfileNewPassword.text?.length ?: 0)
        }

        // Save profile changes
        b.btnSaveProfile.setOnClickListener {
            val name = b.etProfileName.text.toString().trim()
            val email = b.etProfileEmail.text.toString().trim()

            if (name.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter your full name", Toast.LENGTH_SHORT).show()
                b.etProfileName.requestFocus()
                return@setOnClickListener
            }

            if (email.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter a valid email address", Toast.LENGTH_SHORT).show()
                b.etProfileEmail.requestFocus()
                return@setOnClickListener
            }

            Toast.makeText(requireContext(), "Profile updated successfully!", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _b = null
    }
}
