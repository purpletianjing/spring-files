@RestController
@RequestMapping("/file/upload")
public class UploadLocalFileApi {
	/**
	 * 文件上传具体实现方法;
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping("/uploadFile")
	public ApiResponse handleFileUpload(@RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				//get originalFileName as saving path, although this name didn't include path, just a name.
				File targetFile= new File(file.getOriginalFilename());
				System.out.println(targetFile.getAbsolutePath());
				BufferedOutputStream out = new BufferedOutputStream(
						new FileOutputStream(targetFile));
				//write file contents to out, out includes target path.
				out.write(file.getBytes());
				out.flush();
				out.close();
			} catch (Exception e) {
				return new ApiResponse(e);
			}
		} else {
			throw new IllegalArgumentException("Don't contain file.");
		}
		return new ApiResponse();
	}
}