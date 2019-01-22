package com.sekreative.sekreative.ui.addpost

import android.Manifest
import android.app.Activity
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sekreative.sekreative.R
import kotlinx.android.synthetic.main.add_post_fragment.*
import android.content.Intent
import android.graphics.BitmapFactory
import android.R.attr.bitmap
import android.provider.MediaStore
import android.R.attr.data
import com.afollestad.materialdialogs.MaterialDialog
import com.github.florent37.runtimepermission.RuntimePermission.askPermission


class AddPostFragment : Fragment() {

    companion object {
        const val TAG = "AddPostFragment"
        private const val ACTION_PICK_IMAGE = 213
        fun newInstance() = AddPostFragment()
    }

    private lateinit var viewModel: AddPostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_post_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddPostViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_upload_photo.setOnClickListener {
            askPermission(this@AddPostFragment.activity, Manifest.permission.READ_EXTERNAL_STORAGE)
                .onAccepted {
                    val pickIntent = Intent(Intent.ACTION_PICK)
                    pickIntent.type = "image/*"
                    startActivityForResult(pickIntent, ACTION_PICK_IMAGE)
                }
                .onDenied { perm ->
                    MaterialDialog(requireContext())
                        .show {
                            title(res = R.string.title_permission_required)
                            message(res = R.string.message_permission_required)
                            positiveButton(res = R.string.button_ok) {
                                perm.askAgain()
                            }
                            negativeButton(res = R.string.button_no) {
                                it.dismiss()
                            }
                        }
                        .show()
                }
                .onForeverDenied { perm ->
                    MaterialDialog(requireContext())
                        .show {
                            title(res = R.string.title_permission_required)
                            message(res = R.string.message_permission_required)
                            positiveButton(res = R.string.button_ok) {
                                perm.goToSettings()
                            }
                            negativeButton(res = R.string.button_no) {
                                it.dismiss()
                            }
                        }
                        .show()
                }
                .ask()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ACTION_PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            val selectedImageUri = data?.data
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)

            // Get the cursor
            val cursor = activity!!.contentResolver.query(selectedImageUri, filePathColumn, null, null, null)
            // Move to first row
            cursor!!.moveToFirst()

            val columnIndex = cursor.getColumnIndex(filePathColumn[0])
            val imgDecodableString = cursor.getString(columnIndex)
            cursor.close()

            val image = BitmapFactory.decodeFile(imgDecodableString)

            img.setImageBitmap(image)

        }
    }
}
