M.AutoInit();

const images = document.querySelectorAll("img");

for (image of images) {
	image.onerror = () => {
		image.src = "https://search-bank.net/contents/images/3613b9aef50355d5b9189c40a7eab681.jpg";
		const next = image.nextElementSibling;
		if (next) next.innerHTML = "이미지 없음";
	};
	if (image.complete && image.naturalWidth === 0) image.onerror();
}