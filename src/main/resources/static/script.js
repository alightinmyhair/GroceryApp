let myChart = document.getElementById('myChart').getContext('2d');

let groceryItemSumChart = new Chart(myChart, {
    type:'bar',
    data:{
        labels:['Fruits','Vegetables','Grains','Protein'],
        datasets:[{
            label:'Percentage',
            data:[
            1,
            2,
            3,
            4,
            5,
            6
            ]
        }]
    },
    options:{}
});