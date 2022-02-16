# CUSTOMIZE_ME : formula to be included on Homebrew
# Note that there are restrictions on who can be on homebrew
# For example  your repo needs 75 stars and no less than one month old
# You can create your own tap if that's not an option https://docs.brew.sh/Taps
class Transkribe < Formula
  desc "Tool to transform string resources from Android to iOS and vice versa - in Kotlin Multiplatform"
  homepage "https://github.com/LukasAnda/Transkribe"
  url "https://github.com/LukasAnda/Transkribe/archive/refs/tags/v0.3.tar.gz"
  sha256 "bbfaec0b9ed0918feeeed02036e8bddfd5d028f11e09fec8e9af1361a3eaeace"
  license "MIT"

  depends_on "gradle" => :build
  depends_on "openjdk" => :build

  def install
    system "./gradlew", "macosX64Test", "linkReleaseExecutableMacosX64"
    bin.install "build/bin/macosX64/releaseExecutable/transkribe.kexe" => "transkribe"
    bash_completion.install "completions/transkribe.bash" => "transkribe"
    fish_completion.install "completions/transkribe.fish"
    zsh_completion.install "completions/_transkribe.zsh" => "_transkribe"
  end

  test do
    # system "git", "init"
    # testpath/"test").write "test"
    # system "git", "add", "#{testpath}/test"
    # system "git", "commit", "--message", "test"
    # system "git", "config", "--global", "user.name", "tap.user"
    # system "#{bin}/transkribe", "--days", "3"
  end
end