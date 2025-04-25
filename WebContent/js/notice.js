document.addEventListener('DOMContentLoaded', function () {
  const items = document.querySelectorAll('.accordion-item');

  items.forEach((item) => {
    const header = item.querySelector('.accordion-header');

    header.addEventListener('click', function () {
      
      const openedItem = document.querySelector('.accordion-item.active');
      if (openedItem && openedItem !== item) {
        openedItem.classList.remove('active');
      }

     
      item.classList.toggle('active');
    });
  });
});
