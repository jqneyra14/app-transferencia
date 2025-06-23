namespace AFORO255.AZ.Web.DTOs.Movement
{
    public class MovementDTOResponse
    {
        public int IdTransaction { get; set; }
        public decimal Amount { get; set; }
        public string? Type { get; set; }
        public string? CreationDate { get; set; }
        public string? startDate { get; set; }
        public string? endDate { get; set; }
        public string? page { get; set; }
        public string? size { get; set; }
        public int AccountId { get; set; }
    }

    public class TransactionPageResponseDto
    {
        public List<TransactionDto> Transactions { get; set; }
        public int TotalPages { get; set; }
        public int CurrentPage { get; set; }
        public int TotalElements { get; set; }
    }

    public class TransactionDto
    {
        public int Id { get; set; }
        public decimal Amount { get; set; }
        public string Type { get; set; }
        public DateTime Creationdate { get; set; }
        public int AccountId { get; set; }
        public decimal CurrentAmount { get; set; }
        public decimal PreviousAmount { get; set; }
    }
}

